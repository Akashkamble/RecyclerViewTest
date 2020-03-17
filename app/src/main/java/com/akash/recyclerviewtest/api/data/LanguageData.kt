package com.akash.recyclerviewtest.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class Data {
    @JsonClass(generateAdapter = true)
    data class LanguageData(

        val authenticated: Boolean,

        val success: Boolean,

        val response: Response
    )


    @JsonClass(generateAdapter = true)
    data class Response(

        @Json(name = "horizontal_list")
        val horizontalList: List<ListItem>? = null,

        @Json(name = "banner_image")
        val bannerImage: String,

        @Json(name = "total_pages")
        val totalPages: Int,

        val list: List<ListItem>,

        @Json(name = "total_items")
        val totalItems: Int
    )

    @JsonClass(generateAdapter = true)
    data class ListItem(

        val name: String? = null,

        val icon: String? = null
    )
}

