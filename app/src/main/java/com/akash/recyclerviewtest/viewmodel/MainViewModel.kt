package com.akash.recyclerviewtest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.akash.recyclerviewtest.api.Result
import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.base.BaseViewModel
import com.akash.recyclerviewtest.callbacks.ErrorStateRetryListener
import com.akash.recyclerviewtest.callbacks.LoadMoreListener
import com.akash.recyclerviewtest.repository.DataSource
import com.akash.recyclerviewtest.ui.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Akash on 2020-03-16
 */
class MainViewModel(
    private val dataSource: DataSource
) : BaseViewModel(), LoadMoreListener, ErrorStateRetryListener {

    private var page = 1
    private var totalPageCount = 0

    init {
        getDataForPage(page++)
    }

    private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
    fun isLoading(): LiveData<Boolean> = _isLoading

    private val _errorState = ErrorState(this as ErrorStateRetryListener)
    val errorState = MutableLiveData<ErrorState>().apply { value = _errorState }

    private var tempList = mutableListOf<BaseRowModel>()
    private val mutableList = MutableLiveData<List<BaseRowModel>>().apply { value = emptyList() }

    // Add this loadingItem in Pagination.
    private val loadingItem = LoadingItem(this)

    private var loadMoreData = false
    private var totalCount = MutableLiveData<Int>().apply { value = 0 }
    private val totalOfferTillNow = MutableLiveData<Int>().apply { value = 0 }
    val offerCountLiveData: LiveData<String> = Transformations.map(totalOfferTillNow) {
        "Showing offer ${totalOfferTillNow.value} of ${totalCount.value}"
    }

    fun getList(): LiveData<List<BaseRowModel>> = mutableList

    private fun getDataForPage(page: Int) {
        viewModelScope.launch {
            errorState.postValue(_errorState.copy(isLoading = true))
            val result = dataSource.getData(page)
            delay(1000) // to check loading
            handleResult(result)
        }
    }

    private fun handleResult(result: Result<Data.LanguageData>) {
        loadMoreData = when (result) {
            is Result.Success -> {
                makeList(result.data)
                errorState.value = _errorState.copy(isLoading = false)
                _isLoading.value = false
                true
            }
            is Result.Error -> {
                Log.d(TAG, "result -> ${result.errorMessage}")
                if (page > 1) {
                    loadingItem.showLoding(false)
                    loadingItem.setErrorMessage(result.errorMessage)
                }
                errorState.value = _errorState.copy(
                    isError = true,
                    errorMessage = result.errorMessage,
                    showRetry = result.showRetry
                )
                // Decrement page count so after retry we can get data of current page.
                if (page >= 2) page--
                false
            }
        }
    }

    private fun makeList(data: Data.LanguageData) {
        Log.d(TAG, "result -> $data")
        val verticalList = data.response.list
        totalOfferTillNow.postValue(totalOfferTillNow.value!! + verticalList.size)
        totalCount.postValue(data.response.totalItems)
        totalPageCount = data.response.totalPages
        val horizontalList = data.response.horizontalList ?: emptyList()
        val horizontalBaseRowModel = mutableListOf<HorizontalChildItem>()
        if (horizontalList.isNotEmpty()) {
            for (element in horizontalList) {
                horizontalBaseRowModel.add(HorizontalChildItem(element))
            }
        }
        for (element in verticalList) {
            if (tempList.size > 9 && tempList.size % 9 == 0) {
                // This is to add BannerItem in every position divisible by 9
                tempList.add(tempList.size, BannerItem(data.response.bannerImage))
            }
            tempList.add(VerticalListItem(element))
            if (tempList.size == 3 && horizontalList.isNotEmpty())
                tempList.add(HorizontalItem(horizontalBaseRowModel))
            if (tempList.size == 8) {
                tempList.add(BannerItem(data.response.bannerImage))
            }
        }
        // Remove LoadingItem from list to after getting result
        if (tempList.contains(loadingItem)) {
            tempList.remove(loadingItem)
        }
        mutableList.value = tempList
        Log.d(TAG, "tempList -> $tempList")
    }

    override fun onLoadMore() {
        if (page <= totalPageCount && loadMoreData)
            if (tempList.isNotEmpty() && !tempList.contains(loadingItem)) {
                tempList.add(tempList.size, loadingItem)
                mutableList.value = tempList
            }
        getDataForPage(page++)
    }

    override fun onRetry() {
        getDataForPage(page++)
    }
}