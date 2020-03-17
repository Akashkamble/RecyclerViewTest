package com.akash.recyclerviewtest.repository

import com.akash.recyclerviewtest.api.ApiService
import com.akash.recyclerviewtest.base.Result
import com.akash.recyclerviewtest.api.data.Data
import java.lang.Exception

/**
 * Created by Akash on 2020-03-16
 */
class DataSourceImpl(private val apiService: ApiService) : DataSource {
    override suspend fun getData(page: Int): Result<Data.LanguageData> {
        return try {
            val response = apiService.getData(page)
            if (response.isSuccessful){
                Result.Success(response.body()!!)
            } else {
                // Pass error message got from the network call.
                Result.Error("Something went wrong")
            }
        } catch (e : Exception){
            Result.Error(e.localizedMessage ?: "Something went wrong")
        }
    }
}