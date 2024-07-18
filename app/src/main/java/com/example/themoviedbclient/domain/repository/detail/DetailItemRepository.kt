package com.example.themoviedbclient.domain.repository.detail

import com.example.themoviedbclient.data.model.ItemModel

interface DetailItemRepository {
    fun getImageFullPath(path: String): String

    suspend fun saveItem(item: ItemModel)
}