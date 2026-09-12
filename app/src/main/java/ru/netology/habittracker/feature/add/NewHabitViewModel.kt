package ru.netology.habittracker.feature.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.netology.habittracker.data.AppDb
import ru.netology.habittracker.data.HabitEntity

class NewHabitViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDb.getInstance(application).habitDao
    private val _events = MutableSharedFlow<NewHabitEvent>()

    val events = _events.asSharedFlow()

    private val _errorState = MutableStateFlow<Boolean>(false)
    val errorState = _errorState.asStateFlow()

    fun validateAndSave(text: String): Boolean {
        val hasError = text.isBlank()
        _errorState.update { hasError }
        if (!hasError) {
            addHabit(text)
        }
        return !hasError
    }

    fun clearError() {
        _errorState.update { false }
    }

    fun addHabit(name: String) {
        viewModelScope.launch {
            dao.insert(HabitEntity(name = name, statusList = emptyList()))
            _events.emit(NewHabitEvent(name, statusList = emptyList()))
        }
    }
}