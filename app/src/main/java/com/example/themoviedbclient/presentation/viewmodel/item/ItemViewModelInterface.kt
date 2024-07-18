package com.example.themoviedbclient.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel

interface ItemViewModelInterface {
    val loader: LiveData<Boolean>
    val item: LiveData<ItemModel?>
    fun showLoader(state: Boolean)
    fun setItem(item: ItemModel)
    fun getImageFullPath(path: String): String
    suspend fun saveItem()
    suspend fun deleteItem()
}