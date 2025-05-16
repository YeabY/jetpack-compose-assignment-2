package com.yeabsira.todolist.repository

import com.yeabsira.todolist.data.TodoDao
import com.yeabsira.todolist.data.TodoEntity
import com.yeabsira.todolist.network.TodoApiService
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val api: TodoApiService,
    private val dao: TodoDao
) {

    private val sampleTodos = listOf(
        TodoEntity(userId = 7, id = 101, title = "Decrypt classified message", completed = false),
        TodoEntity(userId = 7, id = 102, title = "Retrieve intel from safehouse", completed = true),
        TodoEntity(userId = 7, id = 103, title = "Surveil target at café", completed = false),
        TodoEntity(userId = 7, id = 104, title = "Update HQ on mission status", completed = true),
        TodoEntity(userId = 7, id = 105, title = "Recharge spy drone battery", completed = false),
        TodoEntity(userId = 7, id = 106, title = "Burn after reading: mission file", completed = false),
        TodoEntity(userId = 7, id = 107, title = "Attend covert briefing", completed = true),
        TodoEntity(userId = 7, id = 108, title = "Replace safehouse lock code", completed = false),
        TodoEntity(userId = 7, id = 109, title = "Pack gadgets for next operation", completed = false),
        TodoEntity(userId = 7, id = 110, title = "Create diversion at rendezvous", completed = false),
        TodoEntity(userId = 7, id = 111, title = "Scan area for hidden surveillance", completed = true),
        TodoEntity(userId = 7, id = 112, title = "Send encrypted distress signal", completed = false),
        TodoEntity(userId = 7, id = 113, title = "Analyze enemy movement patterns", completed = false),
        TodoEntity(userId = 7, id = 114, title = "Recover black box from wreckage", completed = false),
        TodoEntity(userId = 7, id = 115, title = "Establish contact with mole", completed = false),
        TodoEntity(userId = 7, id = 116, title = "Evade tail on motorcycle", completed = true),
        TodoEntity(userId = 7, id = 117, title = "Switch disguises undetected", completed = true),
        TodoEntity(userId = 7, id = 118, title = "Tap secure phone line", completed = false),
        TodoEntity(userId = 7, id = 119, title = "Infiltrate gala under cover", completed = false),
        TodoEntity(userId = 7, id = 120, title = "Disable laser grid at vault", completed = false)
    )


    suspend fun getTodos(): List<TodoEntity> {
        dao.deleteAllTodos() // Clear existing data
        dao.insertTodos(sampleTodos) // Insert sample data
        val todos = dao.getAllTodos()
        println("Todos loaded: $todos") // Debug log
        return todos
    }

    suspend fun getTodoById(id: Int): TodoEntity? {
        dao.deleteAllTodos() // Clear existing data
        dao.insertTodos(sampleTodos) // Insert sample data
        val todos = dao.getAllTodos()
        println("Todos loaded for ID $id: $todos") // Debug log
        return todos.find { it.id == id }
    }
}