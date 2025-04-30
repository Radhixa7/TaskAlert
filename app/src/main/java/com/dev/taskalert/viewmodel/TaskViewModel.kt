package com.dev.taskalert.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.taskalert.data.TaskRepository
import com.dev.taskalert.data.TaskRepositoryImpl
import com.dev.taskalert.domain.Task
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository: TaskRepository = TaskRepositoryImpl()

    // Gunakan StateFlow agar UI bisa merespons perubahan data
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        _tasks.value = repository.getTasks() // Inisialisasi data dari repository
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
            _tasks.value = repository.getTasks() // Perbarui state
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            _tasks.value = repository.getTasks() // Perbarui state
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            _tasks.value = repository.getTasks() // Perbarui state
        }
    }
}
