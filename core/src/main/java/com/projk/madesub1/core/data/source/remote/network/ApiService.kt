package com.projk.madesub1.core.data.source.remote.network

import com.projk.madesub1.core.data.source.remote.network.ApiConfig.Companion.token
import com.projk.madesub1.core.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("popular?api_key=$token")
    suspend fun getList(): ListMoviesResponse
}