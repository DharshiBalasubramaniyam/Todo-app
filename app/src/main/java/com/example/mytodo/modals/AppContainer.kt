package com.example.mytodo.modals

import android.content.Context

interface MyToDoAppContainer {
    val tasksRepository: TasksRepository
}

class MyToDoAppDataContainer(private val context: Context) : MyToDoAppContainer {

    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepository(MyToDoDatabase.getDatabase(context).taskDao())
    }
}
