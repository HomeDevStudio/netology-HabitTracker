package ru.netology.habittracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habitEntity: HabitEntity)

    @Query("SELECT * FROM habits WHERE date BETWEEN :dateFrom AND :dateTo")
    fun getHabits(dateFrom: Long, dateTo: Long): Flow<List<HabitEntity>>
}
