package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import com.example.themoviedbclient.data.model.ItemModel

interface SavedItemsViewModelInterface {
    val movies: LiveData<List<ItemModel>>
    val tvShows: LiveData<List<ItemModel>>
    val loader: LiveData<Boolean>
    fun showLoader(state: Boolean)
    fun getImageFullPath(path: String): String
    fun navigateToMovieActionId(): Int
    fun navigateToTvShowActionId(): Int
    fun fetchSavedMovies()
    fun fetchSavedTvShows()
    suspend fun deleteMovie(item: ItemModel)
    suspend fun deleteTvShow(item: ItemModel)
}