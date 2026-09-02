package ru.netology.habittracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("habits")
data class HabitEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("date")
    val date: Long
)
