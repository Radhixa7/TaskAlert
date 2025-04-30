package com.dev.taskalert.data

import com.dev.taskalert.domain.Task

class TaskRepositoryImpl : TaskRepository {
    private val tasks = mutableListOf<Task>()

    override fun getTasks(): List<Task> = tasks

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        }
    }

    override fun deleteTask(task: Task) {
        tasks.removeIf { it.id == task.id }
    }
}
