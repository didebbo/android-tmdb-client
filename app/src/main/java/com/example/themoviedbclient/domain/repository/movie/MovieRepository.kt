package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.movie.Movies
import com.example.themoviedbclient.data.util.Resource

interface MovieRepository {
    suspend fun getMovies(timeWindow: String, language: String): Resource<Movies>
    fun getPosterFulPath(imageFile: String): String
}