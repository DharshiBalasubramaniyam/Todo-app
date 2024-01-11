package com.example.mytodo.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mytodo.MyToDoApplication
import com.example.mytodo.ui.task.HomeViewModel
import com.example.mytodo.ui.task.NewTaskViewModel
import com.example.mytodo.ui.task.TaskInformationViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
//        initializer {
//            ItemEditViewModel(
//                this.createSavedStateHandle(),
//                inventoryApplication().container.itemsRepository
//            )
//        }
        // Initializer for ItemEntryViewModel
        initializer {
            NewTaskViewModel(myToDoApplication().container.tasksRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            TaskInformationViewModel(
                this.createSavedStateHandle(),
                myToDoApplication().container.tasksRepository
            )
        }

         // Initializer for HomeViewModel
        initializer {
            HomeViewModel(myToDoApplication().container.tasksRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.myToDoApplication(): MyToDoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MyToDoApplication)
