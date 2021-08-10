package com.turina1v.oboi.data.network

import com.google.gson.annotations.SerializedName

data class PhotoList(

    @field:SerializedName("hits")
    val photos: List<PhotoItem>? = null

)

data class PhotoItem(

    @field:SerializedName("webformatURL")
    val webformatURL: String? = null,

    @field:SerializedName("largeImageURL")
    val largeImageURL: String? = null,

    @field:SerializedName("id")
    val id: Int? = null

)
