package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.items.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel(), ItemsViewModelInterFace {

    private val _itemsResource: MutableLiveData<Resource<List<ItemModel>>> =
        MutableLiveData(Resource.Null())
    override val itemsResource: LiveData<Resource<List<ItemModel>>> get() = _itemsResource

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    override val loader: LiveData<Boolean> get() = _loader

    override fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    override fun getImageFullPath(path: String): String {
        return moviesRepository.getImageFullPath(path)
    }

    override fun navigateToItemActionId(): Int {
        return R.id.action_movies_to_itemDetail
    }

    override suspend fun fetchItemsResource(){
        _itemsResource.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            _itemsResource.postValue(moviesRepository.getItems())
        }
    }

    override suspend fun saveItem(item: ItemModel) {
        showLoader(true)

        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            moviesRepository.saveItem(item)
            showLoader(false)
        }.invokeOnCompletion {
            viewModelScope.launch(Dispatchers.IO) {
                fetchItemsResource()
            }
        }
    }

    override fun clearInvalidResource() {
        _itemsResource.postValue(Resource.Null())
    }
}