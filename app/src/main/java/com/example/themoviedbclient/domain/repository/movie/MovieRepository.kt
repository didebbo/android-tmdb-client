package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource

interface MovieRepository {
    suspend fun getMovies(timeWindow: String, language: String): Resource<List<ItemModel>>
    fun getPosterFulPath(imageFile: String): String
}