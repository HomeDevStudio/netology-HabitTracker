package ru.netology.habittracker.feature.list

data class HabitListState(
    val items: List<HabitListItem> = emptyList()
)
