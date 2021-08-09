package com.turina1v.oboi.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoNetworkClient {
    private val photoApi: PhotoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        photoApi = retrofit.create(PhotoApi::class.java)
    }

    fun getPhotoList(
        query: String? = null,
        orientation: String? = null,
        category: String? = null,
        colors: String? = null,
        editorsChoice: String? = null,
        order: String? = null,
        page: Int
    ): Single<PhotoList> = photoApi.getPhotoList(
        API_KEY,
        query,
        IMAGE_TYPE,
        orientation,
        category,
        colors,
        editorsChoice,
        order,
        SAFE_SEARCH,
        PER_PAGE,
        page.toString()
    )

    companion object {
        const val BASE_URL = "https://pixabay.com"
        const val API_KEY = "16316299-ba7e12baf00b76dee8fff44c9"
        const val IMAGE_TYPE = "photo"
        const val SAFE_SEARCH = "true"
        const val PER_PAGE = "200"
    }
}

