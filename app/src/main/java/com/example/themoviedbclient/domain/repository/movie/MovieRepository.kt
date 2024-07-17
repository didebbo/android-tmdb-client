package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource

interface MovieRepository {
    suspend fun getMovies(): Resource<List<ItemModel>>
    suspend fun getSavedMovies(): List<ItemModel>
    suspend fun saveMovie(item: ItemModel)
    suspend fun deleteMovie(item: ItemModel)
    fun getImageFullPath(path: String): String
}