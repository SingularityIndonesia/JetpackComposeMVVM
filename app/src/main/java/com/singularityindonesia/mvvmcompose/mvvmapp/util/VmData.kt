package com.singularityindonesia.mvvmcompose.mvvmapp.util

sealed class VmData<out T> {
    fun <R> fold(
        ifIdle: () -> R,
        ifLoading: () -> R,
        ifError: (e: Exception) -> R,
        ifSuccess: (T) -> R,
    ): R =
        when (this) {
            Idle -> ifIdle.invoke()
            Loading -> ifLoading.invoke()
            is Error -> ifError.invoke(e)
            is Success -> ifSuccess.invoke(data)
        }
}

data object Idle : VmData<Nothing>()

data object Loading : VmData<Nothing>()

data class Success<T>(
    val data: T,
) : VmData<T>()

data class Error(
    val e: Exception,
) : VmData<Nothing>()
