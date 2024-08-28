package com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.entity.TodoDisplay

@Composable
fun ListComponent(todos: List<TodoDisplay>) {
    LazyColumn(
        verticalArrangement = Arrangement.run { spacedBy(16.dp) },
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp),
    ) {
        items(todos.size) { index ->
            val data = todos[index]
            TodoCard(todo = data)
        }
    }
}
