package com.example.mytodo.modals

import android.content.Context
import com.example.mytodo.data.AppContainer
import com.example.mytodo.data.InventoryDatabase
import com.example.mytodo.data.ItemsRepository
import com.example.mytodo.data.OfflineItemsRepository

interface MyToDoAppContainer {
    val tasksRepository: TasksRepository
}

class MyToDoAppDataContainer(private val context: Context) : MyToDoAppContainer {

    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepository(MyToDoDatabase.getDatabase(context).taskDao())
    }
}
