package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.domain.repository.items.SavedMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedMoviesViewModel @Inject constructor(
    private val savedMoviesRepository: SavedMoviesRepository
): ViewModel(), SavedItemsViewModelInterface {

    private val _items: MutableLiveData<List<ItemModel>> = MutableLiveData(listOf())
    override val items: LiveData<List<ItemModel>>
        get() = _items

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    override val loader: LiveData<Boolean>
        get() = _loader

    override fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    override fun getImageFullPath(path: String): String {
        return savedMoviesRepository.getImageFullPath(path)
    }

    override fun navigateToItemActionId(): Int {
        return R.id.action_savedMovies_to_itemDetail
    }

    override suspend fun fetchSavedItems() {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            _items.postValue(savedMoviesRepository.getSavedItems())
            showLoader(false)
        }
    }

    override suspend fun deleteItem(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            savedMoviesRepository.deleteItem(item)
            _items.postValue(_items.value?.filter { it.id != item.id } )
            showLoader(false)
        }
    }
}