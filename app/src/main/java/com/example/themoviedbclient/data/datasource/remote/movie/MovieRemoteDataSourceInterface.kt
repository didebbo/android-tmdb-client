package com.example.themoviedbclient.data.datasource.remote.movie

import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import retrofit2.Response

interface MovieRemoteDataSourceInterface {
    suspend fun getMovies(): Response<MoviesDTO>
}