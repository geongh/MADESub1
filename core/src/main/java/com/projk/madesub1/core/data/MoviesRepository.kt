package com.projk.madesub1.core.data

import com.projk.madesub1.core.data.source.local.LocalDataSource
import com.projk.madesub1.core.data.source.remote.RemoteDataSource
import com.projk.madesub1.core.data.source.remote.network.ApiResponse
import com.projk.madesub1.core.data.source.remote.response.MoviesResponse
import com.projk.madesub1.core.domain.model.Movies
import com.projk.madesub1.core.domain.repository.IMoviesRepository
import com.projk.madesub1.core.utils.AppExecutors
import com.projk.madesub1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val moviesList = DataMapper.mapResponseToEntity(data)
                localDataSource.insertMovies(moviesList)
            }

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity((movies))
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }
}