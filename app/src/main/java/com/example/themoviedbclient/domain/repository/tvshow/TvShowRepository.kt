package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource

interface TvShowRepository {

    suspend fun getTvShows(timeWindow: String, language: String): Resource<List<ItemModel>>
    suspend fun getSavedTvShows(): List<ItemModel>
    suspend fun saveTvShow(item: ItemModel)
    suspend fun deleteTvShow(item: ItemModel)
    fun getImageFullPath(path: String): String
}