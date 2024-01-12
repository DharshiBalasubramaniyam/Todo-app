package com.example.mytodo.modals

import kotlinx.coroutines.flow.Flow

class OfflineTasksRepository(private val taskDao: TaskDao) : TasksRepository {

    override fun getAllTasksStream(filterKey: String, searchKey: String): Flow<List<Task>> = taskDao.getAllTasks(filterKey, searchKey)

    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)

    override suspend fun insertTask(task: Task) = taskDao.insert(task)

    override suspend fun deleteTask(task: Task) = taskDao.delete(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)

    override suspend fun updateTaskStatus(id: Int) = taskDao.updateStatus(id)

}