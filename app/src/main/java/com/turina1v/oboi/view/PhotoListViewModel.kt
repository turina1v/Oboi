package com.turina1v.oboi.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turina1v.oboi.domain.GetPhotoListUseCase
import com.turina1v.oboi.domain.SearchProps
import com.turina1v.oboi.data.network.PhotoItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotoListViewModel(private val useCase: GetPhotoListUseCase) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val photoListLiveData = MutableLiveData<List<PhotoItem>>()
    val photoListData: LiveData<List<PhotoItem>>
        get() = photoListLiveData

    init {
        getPhotoList(SearchProps())
    }

    fun getPhotoList(searchProps: SearchProps) {
        disposables.add(useCase.execute(searchProps).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    photoListLiveData.postValue(it)
                },
                {
                    Log.e("", it.toString())
                }))
    }

    override fun onCleared() {
        if (!disposables.isDisposed) disposables.dispose()
        super.onCleared()
    }
}