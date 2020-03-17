package com.akash.recyclerviewtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.recyclerviewtest.repository.DataSource

/**
 * Created by Akash on 2020-03-16
 */
class ViewModelFactory(private val dataSource: DataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataSource) as T
    }
}