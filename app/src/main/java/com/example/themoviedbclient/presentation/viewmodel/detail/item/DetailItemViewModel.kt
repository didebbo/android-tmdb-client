package com.example.themoviedbclient.presentation.viewmodel.detail.item

import androidx.lifecycle.ViewModel
import com.example.themoviedbclient.data.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel

class DetailItemViewModel: ViewModel() {
    private var currentItem: ItemModel? = null

    fun setItem(item: ItemModel) {
        currentItem = item
    }
    fun getItem(): ItemModel? = currentItem
}