package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel

interface SavedItemsViewModelInterface {
    val items: LiveData<List<ItemModel>>
    val loader: LiveData<Boolean>
    fun showLoader(state: Boolean)
    fun getImageFullPath(path: String): String
    fun navigateToItemActionId(): Int
    suspend fun fetchSavedItems()
    suspend fun deleteItem(item: ItemModel)
}