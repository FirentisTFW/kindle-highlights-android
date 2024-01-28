package com.firentistfw.kindlehighlights.common

sealed class RequestState<out T> {
    data class Success<T>(val data: T? = null) : RequestState<T>()
    data class Ongoing<T>(val ongoing: Boolean = true) : RequestState<T>()
    data class Error<T>(val error: String? = null) : RequestState<T>()
}
