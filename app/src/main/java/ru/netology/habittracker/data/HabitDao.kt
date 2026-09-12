package ru.netology.habittracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert
    suspend fun insert(habitEntity: HabitEntity)

    @Update
    suspend fun update(habitEntity: HabitEntity)

    @Delete
    suspend fun delete(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE id = :id")
    fun getById(id: Long): Flow<HabitEntity>

    @Query("SELECT * FROM habits")
    fun getAll(): Flow<List<HabitEntity>>

    @Query("DELETE FROM habits WHERE id = :id")
    suspend fun deleteById(id: Long)
}
