package com.example.themoviedbclient.data.repository.movie

import com.example.themoviedbclient.data.api.MovieApiService
import com.example.themoviedbclient.data.model.MovieList
import com.example.themoviedbclient.data.repository.movie.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val movieApiService: MovieApiService
): MovieRemoteDataSource {
    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String
    ): Response<MovieList> {
        return movieApiService.getTrendingMovies(timeWindow,language)
    }
}