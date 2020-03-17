package com.akash.recyclerviewtest.repository

import com.akash.recyclerviewtest.api.data.Data
import com.akash.recyclerviewtest.api.Result

/**
 * Created by Akash on 2020-03-16
 */
interface DataSource {
    suspend fun getData(page : Int) : Result<Data.LanguageData>
}