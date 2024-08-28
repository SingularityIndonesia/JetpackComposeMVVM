package com.singularityindonesia.mvvmcompose.mvvmapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadTodoListLoading() {
    Column(
        modifier =
            Modifier
                .background(MaterialTheme.colorScheme.background.copy(alpha = .3f))
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        CircularProgressIndicator()
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = "Loading data")
        Spacer(modifier = Modifier.weight(1f))
    }
}
