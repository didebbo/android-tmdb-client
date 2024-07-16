package com.example.themoviedbclient.domain.repository.movie

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.TrendingMovies
import com.example.themoviedbclient.data.util.Resource

interface MovieRepository {
    suspend fun getTrendingMovies(timeWindow: String, language: String): Resource<TrendingMovies>
    fun getPosterFulPath(imageFile: String): String
}