package com.akash.recyclerviewtest.ui

import androidx.lifecycle.MutableLiveData
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.base.BaseRowModel

/**
 * Created by Akash on 2020-03-16
 */
data class HorizontalItem(
    private val itemList: List<HorizontalChildItem>
) : BaseRowModel() {

    val horizontalList
            = MutableLiveData<List<HorizontalChildItem>>().apply { value = itemList }
    override fun setLayoutID() {
        layoutID = R.layout.layout_horizontal_list
    }

    override fun equals(other: Any?): Boolean {
        return if(other is HorizontalItem) itemList == other.itemList else false
    }
}