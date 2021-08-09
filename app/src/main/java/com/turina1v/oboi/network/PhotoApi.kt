package com.turina1v.oboi.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("api")
    fun getPhotoList(
        @Query("key") key: String,
        @Query("q") query: String?,
        @Query("image_type") imageType: String,
        @Query("orientation") orientation: String?,
        @Query("category") category: String?,
        @Query("colors") colors: String?,
        @Query("editors_choice") editorsChoice: String?,
        @Query("order") order: String?,
        @Query("safesearch") safesearch: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): Single<PhotoList>
}