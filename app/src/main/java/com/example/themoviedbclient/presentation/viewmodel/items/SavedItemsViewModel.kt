package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.domain.repository.items.SavedItemRepository
import com.example.themoviedbclient.domain.repository.items.SavedItemsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val savedItemsRepository: SavedItemRepository
): ViewModel(), SavedItemsViewModelInterface {

    private val _moview: MutableLiveData<List<ItemModel>> = MutableLiveData(listOf())
    override val movies: LiveData<List<ItemModel>>
        get() = _moview

    private val _tvShowsResource: MutableLiveData<List<ItemModel>> = MutableLiveData(listOf())
    override val tvShowsResource: LiveData<List<ItemModel>>
        get() = _tvShowsResource

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

    override suspend fun deleteMovie(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            savedItemsRepository.deleteMovie(item)
            showLoader(false)
        }
    }

    override suspend fun deleteTvShow(item: ItemModel) {
        showLoader(false)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            showLoader(false)
        }
    }
}