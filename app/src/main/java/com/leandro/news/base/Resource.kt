package com.leandro.news.base

sealed class Resource<T>(val data : T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>()
    class Error<T>(message: String): Resource<T>(errorMessage = message)
}