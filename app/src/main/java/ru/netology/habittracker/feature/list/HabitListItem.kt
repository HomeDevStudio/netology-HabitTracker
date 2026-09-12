package ru.netology.habittracker.feature.list

data class HabitListItem(
    val id: Long,
    val count: String,
    val name: String,
    val statusList: List<Boolean>
)
