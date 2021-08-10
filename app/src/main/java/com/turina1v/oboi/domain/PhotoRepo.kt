package com.turina1v.oboi.domain

import com.turina1v.oboi.data.network.PhotoList
import io.reactivex.Single

interface PhotoRepo {
    fun getPhotoList(searchProps: SearchProps): Single<PhotoList>
}