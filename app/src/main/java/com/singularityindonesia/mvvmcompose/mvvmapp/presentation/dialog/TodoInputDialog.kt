package com.singularityindonesia.mvvmcompose.mvvmapp.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoInputDialog(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onSubmit: (todo: Todo) -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Enter: `Error` for the title to simulate error")
            Spacer(modifier = Modifier.size(24.dp))
            val title = remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Title") },
                value = title.value,
                onValueChange = { title.value = it },
            )
            Spacer(modifier = Modifier.size(16.dp))
            val content = remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Content") },
                value = content.value,
                onValueChange = { content.value = it },
            )
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    // exception: lambda capture state, i try to make this example simple
                    // do not do this.
                    onSubmit(
                        Todo(
                            title = title.value,
                            content = content.value,
                            dateCreated = System.currentTimeMillis(),
                        ),
                    )
                },
            ) {
                Text(text = "Add to List")
            }
        }
    }
}
