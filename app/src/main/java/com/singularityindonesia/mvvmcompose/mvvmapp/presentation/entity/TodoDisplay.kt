package com.singularityindonesia.mvvmcompose.mvvmapp.presentation.entity

import com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity.Todo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class TodoDisplay(
    val data: Todo,
    val titleDisplay: String,
    val contentDisplay: String,
    val dateCreatedDisplay: String,
) {
    constructor(data: Todo) : this(
        data = data,
        titleDisplay = data.title,
        contentDisplay = data.content,
        dateCreatedDisplay =
            Calendar
                .getInstance()
                .apply {
                    timeInMillis = data.dateCreated
                }.let { calendar ->
                    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    formatter.format(calendar.time)
                },
    )
}
