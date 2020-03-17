package com.akash.recyclerviewtest.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akash.recyclerviewtest.base.BaseRowModel
import com.akash.recyclerviewtest.BR

/**
 * Created by Akash on 2020-03-16
 */
class RecyclerViewBindingAdapter(var data: List<BaseRowModel>, val listener: LoadMoreListener?) :
    RecyclerView.Adapter<RecyclerViewBindingAdapter.ViewHolder>() {

    fun setListData(list: List<BaseRowModel>) {
        data = list
        notifyItemRangeInserted(data.size,list.size)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val dataViewBinding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, i, viewGroup, false)
        return ViewHolder(dataViewBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(data[i])
        if(i == data.size - 1) listener?.onLoadMore()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].layoutID
    }


    class ViewHolder(private val dataViewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataViewBinding.root) {

        fun bind(dataModel: Any) {
            dataViewBinding.setVariable(BR.vm, dataModel)
            dataViewBinding.executePendingBindings()
        }
    }
}