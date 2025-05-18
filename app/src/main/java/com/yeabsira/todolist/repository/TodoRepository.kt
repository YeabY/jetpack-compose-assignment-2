package com.yeabsira.todolist.repository

import com.yeabsira.todolist.data.TodoDao
import com.yeabsira.todolist.data.TodoEntity
import com.yeabsira.todolist.model.toEntity
import com.yeabsira.todolist.network.TodoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val apiService: TodoApiService,
    private val dao: TodoDao
) {

    suspend fun getTodos(): List<TodoEntity> = withContext(Dispatchers.IO) {
        return@withContext try {
            // 1. Try fetching from network
            val remoteTodos = apiService.getTodos() // Should return List<TodoResponse>
            val entities = remoteTodos.map { it.toEntity() } // Convert to List<TodoEntity>

            // 2. Cache the data locally
            dao.insertTodos(entities)

            // 3. Return the fresh data
            entities
        } catch (e: Exception) {
            // 4. On failure, return cached data if available
            val cached = dao.getAllTodos()
            if (cached.isNotEmpty()) {
                cached
            } else {
                // 5. If no cache, rethrow to show error in ViewModel
                throw e
            }
        }
    }

    suspend fun getTodoById(id: Int): TodoEntity? = withContext(Dispatchers.IO) {
        dao.getTodoById(id)
    }
}
