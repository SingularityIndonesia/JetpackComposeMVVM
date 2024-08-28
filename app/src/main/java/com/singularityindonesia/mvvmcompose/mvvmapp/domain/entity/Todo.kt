package com.singularityindonesia.mvvmcompose.mvvmapp.domain.entity

typealias EpochTime = Long

data class Todo(
    val title: String,
    val content: String,
    val dateCreated: EpochTime,
)
