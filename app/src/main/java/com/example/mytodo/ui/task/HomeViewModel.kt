package com.example.mytodo.ui.task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.modals.Task
import com.example.mytodo.modals.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val taskRepository: TasksRepository) : ViewModel() {

    var filterKey by mutableStateOf("")

    var searchKey by mutableStateOf("")

    var homeUiState: StateFlow<HomeUiState> by mutableStateOf(filterTasks())

    private fun filterTasks(): StateFlow<HomeUiState> {
        return taskRepository.getAllTasksStream(filterKey, searchKey).map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    }

    fun onFilterKeyChanged(newFilterKey: String) {
        filterKey = newFilterKey
        homeUiState = filterTasks()
    }

    fun onSearchKeyChanged(newSearchKey: String) {
        searchKey = newSearchKey
        homeUiState = filterTasks()
    }

    suspend fun updateTaskStatus(id: Int) {
        taskRepository.updateTaskStatus(id)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(
    var tasksList: List<Task> = listOf(),
)
