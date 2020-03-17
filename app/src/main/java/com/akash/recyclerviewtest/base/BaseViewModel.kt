package com.akash.recyclerviewtest.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

/**
 * Created by Akash on 2020-03-16
 */

/**
 * BaseViewModel to clear the job when ViewModels instance is destroyed
 */
abstract class BaseViewModel : ViewModel() {
    val TAG = this.javaClass.simpleName

    private val job = SupervisorJob()
    val viewModelScope = CoroutineScope(Dispatchers.Main) + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}