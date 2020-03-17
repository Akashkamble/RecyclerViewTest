package com.akash.recyclerviewtest.base

/**
 * Created by Akash on 2020-03-16
 */
abstract class BaseRowModel {

    val TAG: String = javaClass.name

    var layoutID: Int = 0
        protected set

    init {
        setLayoutID()
    }

    abstract fun setLayoutID()
}