package com.singularityindonesia.mvvmcompose.mvvmapp.data

import com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity.Todo
import com.singularityindonesia.mvvmcompose.mvvmapp.domain.model.TodoListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TodoListModelImpl : TodoListModel {
    private var todoList: List<Todo> =
        (0..3).map {
            Todo(
                title = "Dummy $it",
                content = "Do what ever $it",
                dateCreated = System.currentTimeMillis(),
            )
        }

    override suspend fun addTodoList(todo: Todo): Result<Todo> =
        withContext(Dispatchers.IO) {
            delay(1000)

            if (todo.title.lowercase() == "error") {
                return@withContext Result.failure(IllegalArgumentException())
            }

            todoList += todo
            Result.success(todo)
        }

    override suspend fun getTodoList(): Result<List<Todo>> =
        withContext(Dispatchers.IO) {
            delay(1000)
            Result.success(todoList)
        }
}
