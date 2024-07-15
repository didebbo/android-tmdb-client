package com.example.themoviedbclient.data.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Null<T>(): Resource<T>()
    class Loading<T>(data: T? = null, message: String? = null): Resource<T>(data = data, message = message)
    class Success<T>(data: T?): Resource<T>(data = data)
    class Error<T>(message: String?): Resource<T>(message = message)
}