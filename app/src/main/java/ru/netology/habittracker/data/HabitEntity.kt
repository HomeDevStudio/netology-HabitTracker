package ru.netology.habittracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("habits")
data class HabitEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("statusList")
    val statusList: List<Boolean>
)
