package com.projk.madesub1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.projk.madesub1.core.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies =  moviesUseCase.getAllMovies().asLiveData()
}