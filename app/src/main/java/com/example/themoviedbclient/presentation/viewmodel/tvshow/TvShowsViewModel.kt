package com.example.themoviedbclient.presentation.viewmodel.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.tvshow.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    private val _tvShowsResource: MutableLiveData<Resource<List<ItemModel>>> =
        MutableLiveData(Resource.Null())
    val tvShowsDTOResource: LiveData<Resource<List<ItemModel>>> get() = _tvShowsResource

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    val loader: LiveData<Boolean> get() = _loader

    private fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    fun getImageFullPath(path: String): String {
        return tvShowRepository.getImageFullPath(path)
    }

    suspend fun getTvShowsResource(){
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            _tvShowsResource.postValue(tvShowRepository.getTvShows("day", "en-US"))
            showLoader(false)
        }
    }

    suspend fun saveTvShow(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) // Simulate workflow
            tvShowRepository.saveTvShow(item)
            val data = _tvShowsResource.value?.data?.map {
                if(item.id == it.id) {
                    it.saved = true
                }
                it
            }
            _tvShowsResource.postValue(Resource.Success(data))
            showLoader(false)
        }
    }
}