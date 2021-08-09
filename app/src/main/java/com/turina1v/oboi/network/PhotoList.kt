package com.turina1v.oboi.network

import com.google.gson.annotations.SerializedName

data class PhotoList(

    @field:SerializedName("hits")
    val hits: List<HitsItem?>? = null

)

data class HitsItem(

    @field:SerializedName("webformatURL")
    val webformatURL: String? = null,

    @field:SerializedName("largeImageURL")
    val largeImageURL: String? = null,

    @field:SerializedName("id")
    val id: Int? = null

)
