package ru.netology.habittracker.feature.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.netology.habittracker.data.AppDb
import ru.netology.habittracker.data.HabitEntity

class HabitListViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDb.getInstance(application).habitDao
    private val _habitListState = MutableStateFlow(HabitListState())
    val habitListState = _habitListState.asStateFlow()

    init {
        subscribeToDatabase()
    }

    fun subscribeToDatabase() {
        dao.getAll()
            .map(::mapHabit)
            .onEach { habits ->
                _habitListState.update {
                    HabitListState(items = habits)
                }
            }.launchIn(viewModelScope)
    }

    private fun mapHabit(habits: List<HabitEntity>): List<HabitListItem> =
        habits.map { habit ->
            HabitListItem(
                id = habit.id,
                name = habit.name,
                statusList = habit.statusList,
                count = habit.statusList.count { it }.toString()
            )
        }

    private val _statusesMap = MutableStateFlow<Map<Long, List<Boolean>>>(emptyMap())
    val statusesMap: StateFlow<Map<Long, List<Boolean>>> = _statusesMap.asStateFlow()

    fun loadStatuses(id: Long) {
        viewModelScope.launch {
            if (_statusesMap.value.containsKey(id)) return@launch

            val entity = dao.getById(id).firstOrNull()
            _statusesMap.update { map ->
                map + (id to (entity?.statusList ?: List(7) { false }))
            }
        }
    }

    fun toggleAndSave(index: Int, habitId: Long) {
        val current = _statusesMap.value[habitId] ?: return
        if (index !in current.indices) return

        val updated = current.toMutableList().apply {
            this[index] = !this[index]
        }

        // Обновляем UI
        _statusesMap.update { map ->
            map + (habitId to updated)
        }

        // Сохраняем в БД
        viewModelScope.launch {
            val entity = dao.getById(habitId).firstOrNull()
            if (entity != null) {
                dao.update(entity.copy(statusList = updated))
            }
        }
    }


    val weekProgress: StateFlow<List<Float>> = habitListState
        .map { state ->
            val items = state.items
            if (items.isEmpty()) {
                List(7) { 0f }
            } else {
                (0..6).map { dayIndex ->
                    val checked = items.count { item ->
                        dayIndex < item.statusList.size && item.statusList[dayIndex]
                    }
                    checked.toFloat() / items.size
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = List(7) { 0f }
        )

    fun deleteHabit(id: Long) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }
}