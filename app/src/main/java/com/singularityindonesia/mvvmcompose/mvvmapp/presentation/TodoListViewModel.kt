package com.singularityindonesia.mvvmcompose.mvvmapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singularityindonesia.mvvmcompose.mvvmapp.data.TodoListModelImpl
import com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity.Todo
import com.singularityindonesia.mvvmcompose.mvvmapp.domain.model.TodoListModel
import com.singularityindonesia.mvvmcompose.mvvmapp.util.Error
import com.singularityindonesia.mvvmcompose.mvvmapp.util.Idle
import com.singularityindonesia.mvvmcompose.mvvmapp.util.Loading
import com.singularityindonesia.mvvmcompose.mvvmapp.util.Success
import com.singularityindonesia.mvvmcompose.mvvmapp.util.VmData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val model: TodoListModel = TodoListModelImpl(),
) : ViewModel() {
    private val _todoListDataState = MutableStateFlow<VmData<List<Todo>>>(Idle)
    val loadTodoListDataState = _todoListDataState.asStateFlow()

    fun loadTodoList() {
        _todoListDataState.update { Loading }

        viewModelScope.launch {
            model
                .getTodoList()
                .onSuccess { data ->
                    _todoListDataState.update { Success(data) }
                }.onFailure { e ->
                    _todoListDataState.update { Error(Exception(e)) }
                }
        }
    }

    private val _addTodoDataState = MutableStateFlow<VmData<Todo>>(Idle)
    val addTodoDataState = _addTodoDataState.asStateFlow()

    fun addTodo(todo: Todo) {
        _addTodoDataState.update { Loading }
        viewModelScope.launch {
            model
                .addTodoList(todo)
                .onFailure { e ->
                    _addTodoDataState.update { Error(Exception(e)) }
                }.onSuccess { data ->
                    _addTodoDataState.update { Success(data) }

                    // side effect reload list
                    loadTodoList()
                }
        }
    }

    init {
        loadTodoList()
    }
}
