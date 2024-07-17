package com.example.themoviedbclient.data.datasource.remote.movie

import com.example.themoviedbclient.data.model.movie.Movies
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getTrendingMovies(timeWindow: String, language: String): Response<Movies>
}