package com.projk.madesub1.core.utils

import com.projk.madesub1.core.data.source.local.entity.MoviesEntity
import com.projk.madesub1.core.data.source.remote.network.ApiConfig
import com.projk.madesub1.core.data.source.remote.response.MoviesResponse
import com.projk.madesub1.core.domain.model.Movies

object DataMapper {
    fun mapResponseToEntity(input: List<MoviesResponse>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movie = MoviesEntity(
                id = it.id,
                title = it.title,
                releasedate = it.releasedate,
                storyline = it.storyline,
                image = ApiConfig.imgUrl + it.image,
                isFavorite = false
            )
            moviesList.add(movie)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                title = it.title,
                releasedate = it.releasedate,
                storyline = it.storyline,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movies) = MoviesEntity(
        id = input.id,
        title = input.title,
        releasedate = input.releasedate,
        storyline = input.storyline,
        image = ApiConfig.imgUrl + input.image,
        isFavorite = input.isFavorite
    )
}