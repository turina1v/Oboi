package com.turina1v.oboi.di

import com.turina1v.oboi.data.PhotoRepoImpl
import com.turina1v.oboi.data.network.PhotoNetworkClient
import com.turina1v.oboi.domain.GetPhotoListUseCase
import com.turina1v.oboi.domain.PhotoRepo
import com.turina1v.oboi.view.PhotoListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { PhotoNetworkClient() }
    single<PhotoRepo> { PhotoRepoImpl(get()) }
    single { GetPhotoListUseCase(get()) }
    viewModel { PhotoListViewModel(get()) }
}
