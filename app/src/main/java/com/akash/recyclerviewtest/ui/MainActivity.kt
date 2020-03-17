package com.akash.recyclerviewtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.api.ApiService
import com.akash.recyclerviewtest.databinding.ActivityMainBinding
import com.akash.recyclerviewtest.repository.DataSourceImpl
import com.akash.recyclerviewtest.viewmodel.MainViewModel
import com.akash.recyclerviewtest.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this must be using Dependency Injection for apps scalability.
        factory = ViewModelFactory(DataSourceImpl(ApiService()))

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}
