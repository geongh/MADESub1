package com.projk.madesub1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class MoviesResponse (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("release_date")
    val releasedate: String,
    @field:SerializedName("overview")
    val storyline: String,
    @field:SerializedName("poster_path")
    val image: String
)