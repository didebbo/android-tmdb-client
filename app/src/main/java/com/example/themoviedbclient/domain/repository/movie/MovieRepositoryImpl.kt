package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.MovieList
import com.example.themoviedbclient.data.repository.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String
    ): Resource<MovieList> {
        return responseToResource(movieRemoteDataSource.getTrendingMovies(timeWindow,language))
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if(response.isSuccessful) return Resource.Success(response.body())
        else return Resource.Error(response.message())
    }
}