package com.example.mytodo.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: String,
    val isCompleted: Boolean
)

enum class TaskCategory{
    URGENT, WORK, HOME, PERSONAL
}