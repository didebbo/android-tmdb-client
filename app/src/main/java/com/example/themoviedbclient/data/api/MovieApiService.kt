package com.example.themoviedbclient.data.api

import com.example.themoviedbclient.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/trending/movie/{time_window}")
    suspend fun getTrendingMovies(@Path("time_window") timeWindow: String, @Query("language") language: String): Response<MovieList>
}