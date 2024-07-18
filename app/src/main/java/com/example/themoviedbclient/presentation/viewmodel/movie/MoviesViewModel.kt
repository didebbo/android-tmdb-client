package com.example.themoviedbclient.presentation.viewmodel.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _moviesResource: MutableLiveData<Resource<List<ItemModel>>> =
        MutableLiveData(Resource.Null())
    val moviesResource: LiveData<Resource<List<ItemModel>>> get() = _moviesResource

    private val _loader: MutableLiveData<Boolean> = MutableLiveData(false)
    val loader: LiveData<Boolean> get() = _loader

    private fun showLoader(state: Boolean) {
        _loader.postValue(state)
    }

    fun getImageFullPath(path: String): String {
        return movieRepository.getImageFullPath(path)
    }

    suspend fun getMoviesResource(){
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            _moviesResource.postValue(movieRepository.getMovies())
            showLoader(false)
        }
    }

    suspend fun saveMovie(item: ItemModel) {
        showLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.saveMovie(item)
            val data = _moviesResource.value?.data?.map {
                if(item.id == it.id) {
                    it.saved = true
                }
                it
            }
            _moviesResource.postValue(Resource.Success(data))
            showLoader(false)
        }
    }
}