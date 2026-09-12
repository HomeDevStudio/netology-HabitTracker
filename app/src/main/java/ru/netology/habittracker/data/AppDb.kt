package ru.netology.habittracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HabitEntity::class],
    version = 1
)

@TypeConverters(BooleanListConverter::class)
abstract class AppDb : RoomDatabase() {
    abstract val habitDao: HabitDao

    companion object {
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context = context,
                klass = AppDb::class.java,
                name = "appDb.db"
            ).build().also { INSTANCE = it }
        }
    }
}