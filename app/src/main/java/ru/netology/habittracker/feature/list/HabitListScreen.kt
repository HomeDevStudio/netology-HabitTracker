package ru.netology.habittracker.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HabitListScreenRoute(modifier: Modifier = Modifier) {
    val viewModel = viewModel<HabitListViewModel>()
    LaunchedEffect(Unit) { viewModel.subscribeToDatabase() }
    val state by viewModel.habitListState.collectAsState()
    val weekProgress by viewModel.weekProgress.collectAsStateWithLifecycle()

    HabitListScreen(modifier, state.items.toMutableStateList(), viewModel, weekProgress)
}

@Composable
fun HabitListScreen(
    modifier: Modifier = Modifier,
    list: List<HabitListItem>,
    viewModel: HabitListViewModel,
    weekProgress: List<Float>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        WeekProgressBar(
            Modifier.padding(start = 8.dp, end = 8.dp),
            progress = weekProgress
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = list, key = { it.id }) { item ->
                HabitCardWithSwipe(
                    listItem = item,
                    viewModel = viewModel,
                    onDelete = { id -> viewModel.deleteHabit(id) }
                )
            }
        }
    }
}