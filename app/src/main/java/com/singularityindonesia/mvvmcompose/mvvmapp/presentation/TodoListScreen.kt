package com.singularityindonesia.mvvmcompose.mvvmapp.presentation

import android.R
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component.AddTodoLoading
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component.ListComponent
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component.LoadTodoListLoading
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.dialog.TodoInputDialog
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.entity.TodoDisplay
import kotlinx.coroutines.launch

data class TodoListScreenState(
    val todoList: List<TodoDisplay> = listOf(),
    val showAddTodoLoading: Boolean = false,
    val showAddTodoError: Boolean = false,
    val showLoadTodoLoading: Boolean = false,
    val showLoadTodoError: Boolean = false,
    val showBottomSheetInput: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    vm: TodoListViewModel = remember { TodoListViewModel() },
) {
    val context = LocalContext.current // exception: i try to make this example simple, do not do this
    val scope = rememberCoroutineScope()
    val stateProducer = remember(vm) { TodoListStateProducer(vm) }
    val screenState by stateProducer.screenState.collectAsState(TodoListScreenState())

    LaunchedEffect(screenState.showAddTodoError) {
        if (screenState.showAddTodoError) {
            Toast.makeText(context, "Error adding Todo", Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .then(modifier),
    ) {
        Column {
            Text(text = "Todo List", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.size(8.dp))
            ListComponent(todos = screenState.todoList)
        }
        FloatingActionButton(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            onClick = { stateProducer.showInputBottomSheet() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_add),
                contentDescription = "add todo",
            )
        }
        if (screenState.showLoadTodoLoading) {
            LoadTodoListLoading()
        }
        if (screenState.showAddTodoLoading) {
            AddTodoLoading()
        }
        if (screenState.showBottomSheetInput) {
            val sheetState = rememberModalBottomSheetState()
            TodoInputDialog(
                sheetState = sheetState,
                onDismissRequest = { stateProducer.hideInputBottomSheet() },
                onSubmit = {
                    scope.launch {
                        sheetState.hide()
                        stateProducer.hideInputBottomSheet()
                        vm.addTodo(it)
                    }
                },
            )
        }
    }
}
