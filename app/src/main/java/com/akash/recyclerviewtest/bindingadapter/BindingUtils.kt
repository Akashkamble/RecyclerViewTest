package com.akash.recyclerviewtest.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.callbacks.LoadMoreListener
import com.akash.recyclerviewtest.ui.RecyclerViewBindingAdapter

/**
 * Created by Akash on 2020-03-16
 */


/**
 * @param listData which will be list of BaseRowModel.
 * @param orientation which will decide if RecyclerView is Horizontal or Vertical.
 * @param listener this parameter will invoke onLoadMore() method for pagination.
 */
@BindingAdapter("rowData", "orientation", "loadMoreListener", requireAll = false)
fun RecyclerView.setRowData(listData: List<BaseRowModel>, orientation: Int, listener: LoadMoreListener?) {
    if (listData.isEmpty()) return
    var adapter = this.adapter
    if (adapter == null) {
        adapter = RecyclerViewBindingAdapter(listData, listener)
        this.adapter = adapter
        this.setHasFixedSize(false)
        this.layoutManager = LinearLayoutManager(
            this.context,
            if (orientation == 0)
                LinearLayoutManager.VERTICAL
            else
                LinearLayoutManager.HORIZONTAL,
            false
        )
        this.isNestedScrollingEnabled = false
    } else {
        (adapter as RecyclerViewBindingAdapter).setListData(listData)
    }
}

/**
 * @param url this will be the url of image we want to display in ImageView.
 */
@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    this.load(url) {
        memoryCachePolicy(CachePolicy.READ_ONLY)
    }
}

/**
 * @param isVisible if true the visibility of View will be Visible else Gone.
 */
@BindingAdapter("visible")
fun View.visibleIfTrue(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}