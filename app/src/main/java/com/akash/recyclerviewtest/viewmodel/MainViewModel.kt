package com.akash.recyclerviewtest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akash.recyclerviewtest.api.Result
import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.base.BaseAction
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.base.BaseViewModel
import com.akash.recyclerviewtest.repository.DataSource
import com.akash.recyclerviewtest.ui.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Akash on 2020-03-16
 */
class MainViewModel(private val dataSource: DataSource) : BaseViewModel(), LoadMoreListener {

    sealed class Action : BaseAction {
        data class GetDataForPage(val page: Int) : Action()
    }

    private var page = 1

    init {
        getDataForPage(page++)
    }

    private var tempList = mutableListOf<BaseRowModel>()
    private val mutableList = MutableLiveData<List<BaseRowModel>>().apply { value = emptyList() }
    private val loadingItem = LoadingItem()
    private var loadMoreData = false
    val list: LiveData<List<BaseRowModel>>
        get() = mutableList

    override fun handleAction(action: BaseAction) {
        when (action) {
            is Action.GetDataForPage -> getDataForPage(action.page)
        }
    }

    private fun getDataForPage(page: Int) {

        viewModelScope.launch {
            if (tempList.isNotEmpty()) tempList.add(loadingItem)
            mutableList.postValue(tempList)
            val result = dataSource.getData(page)
            handleResult(result)
        }
    }

    private fun handleResult(result: Result<Data.LanguageData>) {
        loadMoreData = when (result) {
            is Result.Success -> {
                makeList(result.data)
                if (tempList.contains(loadingItem)) {
                    tempList.remove(loadingItem)
                    mutableList.postValue(tempList)
                }
                true
            }
            is Result.Error -> {
                Log.d(TAG, "result -> ${result.errorMessage}")
                if (page > 2) page--
                false
            }
        }
    }

    private fun makeList(data: Data.LanguageData) {
        Log.d(TAG, "result -> $data")
        val verticalList = data.response.list
        val horizontalList = data.response.horizontalList ?: emptyList()
        val horizontalBaseRowModel = mutableListOf<HorizontalChildItem>()
        if (horizontalList.isNotEmpty()) {
            for (element in horizontalList) {
                horizontalBaseRowModel.add(HorizontalChildItem(element))
            }
        }
        val list = mutableListOf<BaseRowModel>()
        for (element in verticalList) {
            list.add(VerticalListItem(element))
            if (list.size == 3 && horizontalList.isNotEmpty())
                list.add(HorizontalItem(horizontalBaseRowModel))
            if (list.size == 8) {
                list.add(BannerItem(data.response.bannerImage))
            }
        }
        tempList.addAll(list)
        mutableList.value = tempList
    }

    override fun onLoadMore() {
        if (page <= 4 && loadMoreData)
            getDataForPage(page++)
    }
}