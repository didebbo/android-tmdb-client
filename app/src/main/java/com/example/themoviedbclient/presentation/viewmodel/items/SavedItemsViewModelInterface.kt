package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource

interface SavedItemsViewModelInterface {
    val movies: LiveData<List<ItemModel>>
    val tvShowsResource: LiveData<List<ItemModel>>
    val loader: LiveData<Boolean>
    fun showLoader(state: Boolean)
    fun getImageFullPath(path: String): String
    fun navigateToMovieActionId(): Int
    fun navigateToTvShowActionId(): Int
    suspend fun deleteMovie(item: ItemModel)
    suspend fun deleteTvShow(item: ItemModel)
}