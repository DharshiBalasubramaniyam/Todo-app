package com.example.mytodo.modals

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * from tasks WHERE category LIKE '%' || :filterKey || '%' AND title LIKE '%' || :searchKey || '%' ORDER BY isCompleted ASC, id ASC")
    fun getAllTasks(filterKey: String, searchKey: String): Flow<List<Task>>

    @Query("SELECT * from tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Update tasks set isCompleted = not(isCompleted) where id = :id")
    suspend fun updateStatus(id: Int)
}