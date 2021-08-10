package com.turina1v.oboi.domain

import com.turina1v.oboi.data.network.PhotoItem
import io.reactivex.Single

class GetPhotoListUseCase(private val repo: PhotoRepo) {
    fun execute(searchProps: SearchProps): Single<List<PhotoItem>> {
        return repo.getPhotoList(searchProps).map {
            it.photos ?: listOf()
        }
    }
}