package com.akash.recyclerviewtest.rowmodels

import androidx.lifecycle.MutableLiveData
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.base.BaseRowModel

/**
 * Created by Akash on 2020-03-16
 */
class HorizontalChildItem(
    private val item : Data.ListItem
) : BaseRowModel(){

    val language = MutableLiveData<String>().apply { value = item.name }
    val languaImage = MutableLiveData<String>().apply { value = item.icon }

    override fun setLayoutID() {
        layoutID = R.layout.layout_horizontal_child
    }
}