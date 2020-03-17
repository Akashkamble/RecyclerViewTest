package com.akash.recyclerviewtest.api

import com.akash.recyclerviewtest.api.data.Data
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Akash on 2020-03-16
 */
interface ApiService {
    @GET("task?")
    suspend fun getData(
        @Query("page") page: Int
    ): Response<Data.LanguageData>

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://www.coupondunia.in")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    }
}