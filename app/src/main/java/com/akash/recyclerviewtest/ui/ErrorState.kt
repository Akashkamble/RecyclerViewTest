package com.akash.recyclerviewtest.ui

import com.akash.recyclerviewtest.callbacks.ErrorStateRetryListener

/**
 * Created by Akash on 2020-03-17
 */
data class ErrorState(
    val listener: ErrorStateRetryListener,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val showRetry: Boolean = false,
    val errorMessage: String? = ""
) {
    fun onRetry() {
        listener.onRetry()
    }

}