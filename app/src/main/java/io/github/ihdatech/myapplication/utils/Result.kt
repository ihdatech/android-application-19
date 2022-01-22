package io.github.ihdatech.myapplication.utils

sealed class Result<out T : Any> {

    class Loading<out T : Any> : Result<T>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure<out T : Any>(val message: Throwable) : Result<T>()

    companion object {
        fun <T : Any> loading(): Result<T> = Loading()
        fun <T : Any> success(data: T): Result<T> = Success(data)
        fun <T : Any> failure(message: Throwable): Result<T> = Failure(message)
    }
}