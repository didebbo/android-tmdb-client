package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.MovieList
import com.example.themoviedbclient.data.util.Resource

interface MovieRepository {
    suspend fun getTrendingMovies(timeWindow: String, language: String): Resource<MovieList>
}