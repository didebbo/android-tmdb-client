package com.example.themoviedbclient.domain.repository.item

import com.example.themoviedbclient.data.model.ItemModel

interface ItemRepository {
    fun getImageFullPath(path: String): String
    suspend fun saveItem(item: ItemModel)
}