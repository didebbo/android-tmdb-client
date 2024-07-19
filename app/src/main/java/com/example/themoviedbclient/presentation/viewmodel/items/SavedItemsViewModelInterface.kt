package com.example.themoviedbclient.presentation.viewmodel.items

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel

interface SavedItemsViewModelInterface {
    val items: LiveData<List<ItemModel>>
    val loader: LiveData<Boolean>
    fun showLoader(state: Boolean)
    fun getImageFullPath(path: String): String
    fun navigateToItemIdWithArgs(): Pair<Int, Bundle?>
    suspend fun fetchSavedItems()
    suspend fun deleteItem(item: ItemModel)
}