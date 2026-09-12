package ru.netology.habittracker.feature.add

data class NewHabitEvent(
    val name: String,
    val statusList: List<Boolean>
)
