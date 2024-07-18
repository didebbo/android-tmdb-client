package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

interface ItemsViewModelInterFace {
    val itemsResource: LiveData<Resource<List<ItemModel>>>
    val loader: LiveData<Boolean>
    fun showLoader(state: Boolean)
    fun getImageFullPath(path: String): String
    fun navigateToItemActionId(): Int
    suspend fun fetchItemsResource()
    suspend fun saveItem(item: ItemModel)
}