package com.singularityindonesia.mvvmcompose.mvvmapp.domain.model

import com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity.Todo

interface TodoListModel {
    suspend fun addTodoList(todo: Todo): Result<Todo>

    suspend fun getTodoList(): Result<List<Todo>>
}
