package com.example.mytodo.ui.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mytodo.modals.Task
import com.example.mytodo.modals.TaskCategory
import com.example.mytodo.modals.TasksRepository


class NewTaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }

    suspend fun saveTask() {
        if (validateInput()) {
            tasksRepository.insertTask(taskUiState.taskDetails.toTask())
        }
    }

    private fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && isCompleted.isNotBlank() && category.isNotBlank()
        }
    }
}

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails(),
    val isEntryValid: Boolean = false
)

data class TaskDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = TaskCategory.WORK.toString(),
    val isCompleted: String = "false",
)

fun TaskDetails.toTask(): Task = Task(
    id = id,
    title = title,
    description = description,
    category = category,
    isCompleted = isCompleted.toBoolean()
)

fun Task.toTaskUiState(isEntryValid: Boolean = false): TaskUiState = TaskUiState(
    taskDetails = this.toTaskDetails(),
    isEntryValid = isEntryValid
)

fun Task.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    title = title,
    description = description,
    category = category,
    isCompleted = isCompleted.toString()
)