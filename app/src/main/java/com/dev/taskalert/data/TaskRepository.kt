package com.dev.taskalert.data

import com.dev.taskalert.domain.Task

interface TaskRepository {
    fun getTasks(): List<Task>
    fun addTask(task: Task)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
}
