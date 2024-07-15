package com.example.themoviedbclient.data.repository.movie

import com.example.themoviedbclient.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getTrendingMovies(timeWindow: String, language: String): Response<MovieList>
}