package com.example.themoviedbclient.data.datasource.remote.movie

import com.example.themoviedbclient.data.api.movie.MovieApiService
import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import retrofit2.Response

class MovieRemoteDataSource(
    private val movieApiService: MovieApiService,
): MovieRemoteDataSourceInterface {
    override suspend fun getMovies(): Response<MoviesDTO> {
        return movieApiService.getMovies()
    }
}