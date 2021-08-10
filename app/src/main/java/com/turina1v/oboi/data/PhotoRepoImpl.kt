package com.turina1v.oboi.data

import com.turina1v.oboi.data.network.PhotoList
import com.turina1v.oboi.data.network.PhotoNetworkClient
import com.turina1v.oboi.domain.PhotoRepo
import com.turina1v.oboi.domain.SearchProps
import io.reactivex.Single

class PhotoRepoImpl(private val networkClient: PhotoNetworkClient) : PhotoRepo {
    override fun getPhotoList(searchProps: SearchProps): Single<PhotoList> = networkClient.getPhotoList(searchProps)
}