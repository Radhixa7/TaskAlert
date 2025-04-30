package com.dev.taskalert.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.taskalert.domain.Task
import com.dev.taskalert.viewmodel.TaskViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    var showEditDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Daftar Tugas", style = MaterialTheme.typography.headlineSmall)

            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task,
                        onTaskClick = {
                            viewModel.updateTask(task.copy(isCompleted = !task.isCompleted))
                        },
                        onEditClick = {
                            taskToEdit = task
                            showEditDialog = true
                        },
                        onDeleteClick = {
                            viewModel.deleteTask(task)
                        }
                    )
                }
            }

            if (showEditDialog && taskToEdit != null) {
                EditTaskDialog(
                    task = taskToEdit!!,
                    onDismiss = { showEditDialog = false },
                    onConfirm = { updatedTask ->
                        viewModel.updateTask(updatedTask)
                        showEditDialog = false
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                viewModel.addTask(
                    Task(
                        id = tasks.size + 1,
                        title = "Tugas Baru",
                        description = "Deskripsi anyar",
                        deadline = System.currentTimeMillis(),
                        priority = 1
                    )
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f).clickable { onTaskClick() }) {
                    Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = task.description, style = MaterialTheme.typography.bodySmall)
                    Text(
                        text = if (task.isCompleted) "✅ Selesai" else "⏳ Belum Selesai",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Row {
                    IconButton(onClick = onEditClick) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(Icons.Default.Delete, contentDescription = "Hapus")
                    }
                }
            }
        }
    }
}

@Composable
fun EditTaskDialog(
    task: Task,
    onDismiss: () -> Unit,
    onConfirm: (Task) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Tugas") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Judul") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Deskripsi") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(task.copy(title = title, description = description))
            }) {
                Text("Simpan")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}

