package com.example.mytodo.ui.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

    private var filterKey by mutableStateOf("")

    var homeUiState: StateFlow<HomeUiState> =
        taskRepository.getAllTasksStream(filterKey).map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    suspend fun updateTaskStatus(id: Int) {
        taskRepository.updateTaskStatus(id)
    }

    fun updateFilterKey(newFilterKey: String) {
        filterKey = newFilterKey
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val tasksList: List<Task> = listOf())