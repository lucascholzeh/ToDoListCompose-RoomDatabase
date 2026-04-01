package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.data.local.AppDatabase
import br.edu.satc.todolistcompose.data.repository.TaskRepository
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import br.edu.satc.todolistcompose.ui.viewmodel.TaskViewModel
import br.edu.satc.todolistcompose.util.ThemeMode
import br.edu.satc.todolistcompose.util.ThemePreference

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = applicationContext
            val themePref = ThemePreference(context)

            var themeMode by remember { mutableStateOf(themePref.get()) }

            val db = AppDatabase.getDatabase(context)
            val repository = TaskRepository(db.taskDao())
            val viewModel = TaskViewModel(repository)

            ToDoListComposeTheme(themeMode = themeMode) {
                HomeScreen(
                    viewModel = viewModel,
                    onToggleTheme = {
                        themeMode = if (themeMode == ThemeMode.LIGHT)
                            ThemeMode.DARK
                        else
                            ThemeMode.LIGHT

                        themePref.save(themeMode)
                    }
                )
            }
        }
    }
}
