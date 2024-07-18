package com.example.themoviedbclient.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel

interface ItemViewModel {
    val loader: LiveData<Boolean>
    val item: LiveData<ItemModel?>
    fun showLoader(state: Boolean)
    fun setItem(item: ItemModel)
    fun getImageFullPath(path: String): String
    suspend fun saveItem(item: ItemModel)
}