package ru.netology.habittracker.data

import androidx.room.TypeConverter

object BooleanListConverter {
    private const val DEFAULT_SIZE = 7

    @TypeConverter
    fun fromList(list: List<Boolean>): String {
        val normalized = if (list.size != DEFAULT_SIZE) {
            List(DEFAULT_SIZE) { i -> if (i < list.size) list[i] else false }
        } else list
        return normalized.joinToString("") { if (it) "1" else "0" }
    }

    @TypeConverter
    fun toList(value: String): List<Boolean> {
        if (value.isBlank()) {
            return List(DEFAULT_SIZE) { false }
        }
        return value.map { it == '1' }.let {
            if (it.size != DEFAULT_SIZE) List(DEFAULT_SIZE) { false } else it
        }
    }
}
