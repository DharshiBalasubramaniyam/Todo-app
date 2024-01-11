package com.example.mytodo.modals

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class MyToDoDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: MyToDoDatabase? = null

        fun getDatabase(context: Context): MyToDoDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MyToDoDatabase::class.java, "myToDo_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}