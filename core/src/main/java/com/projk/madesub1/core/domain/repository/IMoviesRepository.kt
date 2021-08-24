package com.projk.madesub1.core.domain.repository

import com.projk.madesub1.core.data.Resource
import com.projk.madesub1.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)
}