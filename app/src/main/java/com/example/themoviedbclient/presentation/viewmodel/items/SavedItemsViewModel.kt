package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.domain.repository.items.SavedItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val savedItemsRepository: SavedItemRepository
): ViewModel(), SavedItemsViewModelInterface {

    private val _movies: MutableLiveData<List<ItemModel>> = MutableLiveData(listOf())
    override val movies: LiveData<List<ItemModel>>
        get() = _movies

    private val _tvShows: MutableLiveData<List<ItemModel>> = MutableLiveData(listOf())
    override val tvShows: LiveData<List<ItemModel>>
        get() = _tvShows

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    override val loader: LiveData<Boolean>
        get() = _loader

    override fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    override fun getImageFullPath(path: String): String {
        return savedItemsRepository.getImageFullPath(path)
    }

    override fun navigateToMovieActionId(): Int {
        TODO("Not yet implemented")
    }

    override fun navigateToTvShowActionId(): Int {
        TODO("Not yet implemented")
    }

    override fun fetchSavedMovies() {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            _movies.postValue(savedItemsRepository.getSavedMovies())
            showLoader(false)
        }
    }

    override fun fetchSavedTvShows() {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            _tvShows.postValue(savedItemsRepository.getSavedTvShows())
            showLoader(false)
        }
    }

    override suspend fun deleteMovie(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            savedItemsRepository.deleteMovie(item)
            _movies.postValue(_movies.value?.filter { it.id != item.id } )
            showLoader(false)
        }
    }

    override suspend fun deleteTvShow(item: ItemModel) {
        showLoader(false)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            savedItemsRepository.deleteTvShow(item)
            _tvShows.postValue(_tvShows.value?.filter { it.id != item.id } )
            showLoader(false)
        }
    }
}