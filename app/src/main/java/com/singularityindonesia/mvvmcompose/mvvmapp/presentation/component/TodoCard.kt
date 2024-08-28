package com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singularityindonesia.mvvmcompose.mvvmapp.presentation.entity.TodoDisplay

@Composable
fun TodoCard(todo: TodoDisplay) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = todo.titleDisplay, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Created at ${todo.dateCreatedDisplay}",
                style = MaterialTheme.typography.labelSmall,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = todo.contentDisplay, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
