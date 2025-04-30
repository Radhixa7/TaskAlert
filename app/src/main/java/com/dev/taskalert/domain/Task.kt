package com.dev.taskalert.domain

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val deadline: Long,
    val priority: Int,
    var isCompleted: Boolean = false
)
