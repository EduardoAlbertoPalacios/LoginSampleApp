package com.baubap.shared.common

sealed class ProcessResult<out V> {
    data class Success<out T>(val data: T) : ProcessResult<T>()
    data class Error(val exception: Throwable) : ProcessResult<Nothing>()
}
