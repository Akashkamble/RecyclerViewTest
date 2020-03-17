package com.akash.recyclerviewtest.viewmodel

import android.util.Log
import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.ui.BannerItem
import com.akash.recyclerviewtest.ui.HorizontalChildItem
import com.akash.recyclerviewtest.ui.HorizontalItem
import com.akash.recyclerviewtest.ui.VerticalListItem

/**
 * Created by Akash on 2020-03-17
 */
object ListUtil {
    @JvmStatic
    fun makeList(data: Data.LanguageData, tempList : MutableList<BaseRowModel>) {
        val verticalList = data.response.list

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
    }
}