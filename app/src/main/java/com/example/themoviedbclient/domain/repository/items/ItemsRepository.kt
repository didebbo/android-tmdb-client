package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource

interface ItemsRepository {
    suspend fun getItems(): Resource<List<ItemModel>>
    suspend fun getSavedItems(): List<ItemModel>
    suspend fun saveItem(item: ItemModel)
    suspend fun deleteItem(item: ItemModel)
    fun getImageFullPath(path: String): String
}