package br.edu.satc.todolistcompose.data.repository

import br.edu.satc.todolistcompose.data.local.TaskDao
import br.edu.satc.todolistcompose.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    val tasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun insert(task: TaskEntity) = dao.insert(task)

    suspend fun update(task: TaskEntity) = dao.update(task)

    suspend fun delete(task: TaskEntity) {
        dao.delete(task)
    }
}