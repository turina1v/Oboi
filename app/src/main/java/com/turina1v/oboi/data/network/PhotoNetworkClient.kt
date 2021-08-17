package com.turina1v.oboi.data.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.turina1v.oboi.domain.SearchProps
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
        searchProps: SearchProps
    ): Single<PhotoList> = photoApi.getPhotoList(
        API_KEY,
        searchProps.query,
        IMAGE_TYPE,
        searchProps.orientation,
        searchProps.category,
        searchProps.getColorsQuery(),
        searchProps.editorsChoice,
        searchProps.order,
        SAFE_SEARCH,
        PER_PAGE,
        if (searchProps.page == null) null else searchProps.page.toString()
    )

    companion object {
        const val BASE_URL = "https://pixabay.com"
        const val API_KEY = "your_api_key"
        const val IMAGE_TYPE = "photo"
        const val SAFE_SEARCH = "true"
        const val PER_PAGE = "200"
    }
}

