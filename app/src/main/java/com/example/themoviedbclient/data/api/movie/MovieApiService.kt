package com.example.themoviedbclient.data.api.movie

import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/trending/movie/{time_window}")
    suspend fun getMovies(
        @Path("time_window") timeWindow: String = "day",
        @Query("language") language: String = "en-US"
    ): Response<MoviesDTO>
}