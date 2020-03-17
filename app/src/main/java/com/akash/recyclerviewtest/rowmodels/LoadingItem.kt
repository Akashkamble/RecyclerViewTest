package com.akash.recyclerviewtest.rowmodels

import androidx.databinding.ObservableField
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.callbacks.ErrorStateRetryListener

/**
 * Created by Akash on 2020-03-17
 */
class LoadingItem(
    private val listener : ErrorStateRetryListener
) : BaseRowModel() {
    val errorMessage = ObservableField<String>()
    val loading = ObservableField<Boolean>().apply { set(true) }

    fun setErrorMessage(message: String) {
        errorMessage.set(message)
    }

    fun showLoding(isLoading: Boolean) {
        loading.set(isLoading)
    }
    // Retry when error comes in pagination
    fun onRetry(){
        showLoding(true)
        listener.onRetry()
    }

    override fun setLayoutID() {
        layoutID = R.layout.layout_loading
    }
}