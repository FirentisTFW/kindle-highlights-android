package com.firentistfw.kindlehighlights.common

sealed class DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>()
    data class Loading<T>(val loading: Boolean = true) : DataState<T>()
    data class Error<T>(val error: String? = null) : DataState<T>()
}
