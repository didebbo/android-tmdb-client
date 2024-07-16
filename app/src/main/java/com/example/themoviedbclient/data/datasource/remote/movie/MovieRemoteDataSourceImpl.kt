package com.example.themoviedbclient.data.datasource.remote.movie

import com.example.themoviedbclient.data.api.MovieApiService
import com.example.themoviedbclient.data.model.TrendingMovies
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val movieApiService: MovieApiService,
): MovieRemoteDataSource {
    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String
    ): Response<TrendingMovies> {
        return movieApiService.getTrendingMovies(timeWindow,language)
    }
}