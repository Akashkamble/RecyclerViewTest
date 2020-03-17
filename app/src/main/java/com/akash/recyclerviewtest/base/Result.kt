package com.akash.recyclerviewtest.base

/**
 * Created by Akash on 2020-03-16
 */

/**
 * Common Result Wrapper for Async calls
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String, val showRetry: Boolean = true) : Result<Nothing>()
}