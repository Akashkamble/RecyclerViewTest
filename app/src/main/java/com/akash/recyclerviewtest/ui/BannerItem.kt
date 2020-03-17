package com.akash.recyclerviewtest.ui

import androidx.lifecycle.MutableLiveData
import com.akash.recyclerviewtest.R
import com.akash.recyclerviewtest.base.BaseRowModel

/**
 * Created by Akash on 2020-03-16
 */
class BannerItem(
    val item: String
) : BaseRowModel() {
    val bannerImage = MutableLiveData<String>().apply { value = item }
    override fun setLayoutID() {
        layoutID = R.layout.layout_banner
    }

    override fun equals(other: Any?): Boolean {
        return if(other is BannerItem) item == other.item else false
    }
}