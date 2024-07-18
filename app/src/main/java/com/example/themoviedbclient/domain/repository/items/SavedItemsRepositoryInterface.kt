package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.model.ItemModel

interface SavedItemsRepositoryInterface {
    suspend fun getSavedMovies(): List<ItemModel>
    suspend fun getSavedTvShows(): List<ItemModel>
    suspend fun deleteMovie(item: ItemModel)
    suspend fun deleteTvShow(item: ItemModel)
    fun getImageFullPath(path: String): String
}