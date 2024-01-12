package com.example.mytodo.modals

import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAllTasksStream(filterKey: String, searchKey: String): Flow<List<Task>>

    fun getTaskStream(id: Int): Flow<Task?>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun updateTaskStatus(id: Int)
}