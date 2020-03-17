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
 * BaseViewModel to clear the job when ViewModels instance is destroyed.
 */
abstract class BaseViewModel : ViewModel() {
    val TAG = this.javaClass.simpleName

    private val job = SupervisorJob()
    val viewModelScope = CoroutineScope(Dispatchers.Main) + job

    /*
        This function is not needed in this project.
        but we can pass the Actions from View to ViewModel without invoking methods of ViewModels from views.
     */
    //abstract fun handleAction(action: BaseAction)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}