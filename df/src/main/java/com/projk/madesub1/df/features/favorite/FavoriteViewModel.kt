package com.projk.madesub1.df.features.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.projk.madesub1.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val favoriteMovies = moviesUseCase.getFavoriteMovies().asLiveData()
}