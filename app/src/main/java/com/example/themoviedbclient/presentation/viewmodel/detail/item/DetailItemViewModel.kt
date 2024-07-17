package com.example.themoviedbclient.presentation.viewmodel.detail.item

import androidx.lifecycle.ViewModel
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.domain.repository.detail.item.DetailItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailItemViewModel @Inject constructor(
    private val detailItemRepository: DetailItemRepository
): ViewModel() {
    private var currentItem: ItemModel? = null

    fun setItem(item: ItemModel) {
        currentItem = item
    }
    fun getItem(): ItemModel? = currentItem

    fun getImageFullPath(path: String): String {
        return detailItemRepository.getImageFullPath(path)
    }
}