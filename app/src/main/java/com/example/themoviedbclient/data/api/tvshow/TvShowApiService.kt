package com.example.themoviedbclient.data.api.tvshow

import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("3/trending/tv/{time_window}")
    suspend fun getTvShows(
        @Path("time_window") timeWindow: String = "day",
        @Query("language") language: String = "en-US"
    ): Response<TvShowsDTO>
}