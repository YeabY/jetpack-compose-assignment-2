package com.yeabsira.todolist.network

import com.yeabsira.todolist.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}