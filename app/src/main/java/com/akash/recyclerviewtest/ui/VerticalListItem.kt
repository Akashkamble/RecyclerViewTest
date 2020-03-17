package com.akash.recyclerviewtest.ui

import androidx.lifecycle.MutableLiveData
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.base.BaseRowModel

/**
 * Created by Akash on 2020-03-16
 */
data class VerticalListItem(
    private val item : Data.ListItem
) : BaseRowModel() {

    val language = MutableLiveData<String>().apply { value = item.name }
    val languaImage = MutableLiveData<String>().apply { value = item.icon }

    override fun setLayoutID() {
        layoutID = R.layout.layout_vertical_item
    }

    override fun equals(other: Any?): Boolean {
        return if(other is VerticalListItem) language == other.languaImage else false
    }

    override fun hashCode(): Int {
        var result = item.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + languaImage.hashCode()
        return result
    }
}