package com.projk.madesub1.di

import com.projk.madesub1.core.domain.usecase.MoviesInteractor
import com.projk.madesub1.core.domain.usecase.MoviesUseCase
import com.projk.madesub1.detail.DetailMoviesViewModel
import com.projk.madesub1.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
}
