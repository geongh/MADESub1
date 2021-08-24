package com.projk.madesub1.detail

import androidx.lifecycle.ViewModel
import com.projk.madesub1.core.domain.model.Movies
import com.projk.madesub1.core.domain.usecase.MoviesUseCase

class DetailMoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun setFavoriteMovies(movies: Movies, newState: Boolean) = moviesUseCase.setFavoriteMovies(movies, newState)
}