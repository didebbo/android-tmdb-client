package com.example.themoviedbclient.data.api

import com.example.themoviedbclient.data.model.tvshow.TvShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("3/trending/tv/{time_window}")
    suspend fun getTvShows(@Path("time_window") timeWindow: String, @Query("language") language: String): Response<TvShows>
}