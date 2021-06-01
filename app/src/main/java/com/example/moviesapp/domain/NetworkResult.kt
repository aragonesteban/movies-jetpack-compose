package com.example.moviesapp.domain

sealed class NetworkResult<out T: Any> {
    data class Success<out T: Any>(val data: T): NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
}
