package com.example.themoviedbclient.presentation.viewmodel.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.R
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.items.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val tvShowsRepository: TvShowsRepository
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
        return tvShowsRepository.getImageFullPath(path)
    }

    override fun navigateToItemActionId(): Int {
        return R.id.action_tvShows_to_itemDetail
    }

    override suspend fun fetchItemsResource(){
        _itemsResource.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            _itemsResource.postValue(tvShowsRepository.getItems())
        }
    }

    override suspend fun saveItem(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) // Simulate workflow
            tvShowsRepository.saveItem(item)
            val data = _itemsResource.value?.data?.map {
                if(it.id == item.id) it.saved = true
                it
            }
            _itemsResource.postValue(Resource.Success(data))
            showLoader(false)
        }
    }

    override fun clearInvalidResource() {
        _itemsResource.postValue(Resource.Null())
    }
}