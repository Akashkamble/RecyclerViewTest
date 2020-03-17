package com.akash.recyclerviewtest.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class Data {
    @JsonClass(generateAdapter = true)
    data class LanguageData(

        @Json(name = "authenticated")
        val authenticated: Boolean,

        @Json(name = "success")
        val success: Boolean,

        @Json(name = "response")
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

        @Json(name = "list")
        val list: List<ListItem>,

        @Json(name = "total_items")
        val totalItems: Int
    )
/*
    @JsonClass(generateAdapter = true)
    data class HorizontalListItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "icon")
        val icon: String? = null
    )*/


    @JsonClass(generateAdapter = true)
    data class ListItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "icon")
        val icon: String? = null
    )
}

