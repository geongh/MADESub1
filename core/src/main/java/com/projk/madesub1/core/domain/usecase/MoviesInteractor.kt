package com.projk.madesub1.core.domain.usecase
import com.projk.madesub1.core.domain.model.Movies
import com.projk.madesub1.core.domain.repository.IMoviesRepository

class MoviesInteractor(private val moviesRepository: IMoviesRepository): MoviesUseCase {
    override fun getAllMovies() = moviesRepository.getAllMovies()
    override fun getFavoriteMovies() = moviesRepository.getFavoriteMovies()
    override fun setFavoriteMovies(movies: Movies, state: Boolean) = moviesRepository.setFavoriteMovies(movies, state)

}