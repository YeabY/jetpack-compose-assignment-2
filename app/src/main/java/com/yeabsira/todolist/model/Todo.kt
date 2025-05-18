package com.yeabsira.todolist.model

import com.yeabsira.todolist.data.TodoEntity

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)


fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )
}

