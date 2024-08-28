package com.singularityindonesia.mvvmcompose.mvvmapp.presentation

import androidx.lifecycle.viewModelScope
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.entity.TodoDisplay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * This class is created to handle the calculations needed by the UI to reducce data into ready-to-display data.
 * Data from ViewModel into Ready to display UI State.
 */
class TodoListStateProducer(
    private val vm: TodoListViewModel,
) {
    private val scope = vm.viewModelScope
    private val _screenState = MutableStateFlow(TodoListScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        observeLoadTodo()
        observeAddTodo()
    }

    private fun observeLoadTodo() {
        scope.launch {
            vm.loadTodoListDataState.collect { dataState ->
                val newState =
                    dataState.fold(
                        ifIdle = { screenState.value },
                        ifLoading = {
                            screenState.value.copy(
                                showLoadTodoLoading = true,
                            )
                        },
                        ifError = {
                            screenState.value.copy(
                                showLoadTodoLoading = false,
                                showLoadTodoError = true,
                            )
                        },
                        ifSuccess = { data ->
                            screenState.value.copy(
                                todoList = data.map(::TodoDisplay),
                                showLoadTodoLoading = false,
                            )
                        },
                    )

                _screenState.update { newState }
            }
        }
    }

    private fun observeAddTodo() {
        scope.launch {
            vm.addTodoDataState.collect { dataState ->
                val newState =
                    dataState.fold(
                        ifIdle = { screenState.value },
                        ifLoading = {
                            screenState.value.copy(
                                showAddTodoLoading = true,
                            )
                        },
                        ifError = {
                            screenState.value.copy(
                                showAddTodoLoading = false,
                                showAddTodoError = true,
                            )
                        },
                        ifSuccess = {
                            screenState.value.copy(
                                showAddTodoLoading = false,
                            )
                        },
                    )

                _screenState.update { newState }
            }
        }
    }

    fun showInputBottomSheet() {
        _screenState.update { it.copy(showBottomSheetInput = true) }
    }

    fun hideInputBottomSheet() {
        _screenState.update { it.copy(showBottomSheetInput = false) }
    }
}
