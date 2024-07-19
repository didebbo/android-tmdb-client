package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.model.ItemModel

interface SavedItemsRepositoryInterface {
    suspend fun getSavedItems(): List<ItemModel>
    suspend fun deleteItem(item: ItemModel)
    fun getImageFullPath(path: String): String
}