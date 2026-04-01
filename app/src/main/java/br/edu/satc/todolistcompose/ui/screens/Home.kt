@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.satc.todolistcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.components.TaskCard
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import br.edu.satc.todolistcompose.ui.viewmodel.TaskViewModel
import br.edu.satc.todolistcompose.util.ThemeMode
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    ToDoListComposeTheme(themeMode = ThemeMode.LIGHT) {
        Text("Preview")
    }
}

@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onToggleTheme: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Content(viewModel)

        NewTask(viewModel)

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // BOTÃO DE TEMA
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(end = 8.dp),
                text = { Text("Tema") },
                icon = { Icon(Icons.Default.Add, contentDescription = "") }, // depois pode trocar ícone
                onClick = onToggleTheme
            )

            // BOTÃO NOVA TASK
            ExtendedFloatingActionButton(
                text = { Text("Nova tarefa") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    viewModel.openBottomSheet()
                }
            )
        }
    }
}

@Composable
fun Content(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks) { task ->
            TaskCard(
                taskData = TaskData(
                    id = task.id,
                    title = task.title,
                    description = task.description,
                    complete = task.isCompleted
                ),
                onTaskCheckedChange = {
                    viewModel.toggleTask(task)
                },
                onDeleteClick = {
                    viewModel.deleteTask(task)
                }
            )
        }
    }
}

@Composable
fun NewTask(viewModel: TaskViewModel) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    if (viewModel.showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.closeBottomSheet()
            },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Título da tarefa") }
                )

                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text("Descrição da tarefa") }
                )

                Button(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = {
                        viewModel.addTask(taskTitle, taskDescription)

                        taskTitle = ""
                        taskDescription = ""

                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                viewModel.closeBottomSheet()
                            }
                        }
                    }
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}