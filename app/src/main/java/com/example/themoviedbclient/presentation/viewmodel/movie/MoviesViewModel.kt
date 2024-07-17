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

    private var _moviesResource: MutableLiveData<Resource<List<ItemModel>>> =
        MutableLiveData(Resource.Null())

    val moviesResource: LiveData<Resource<List<ItemModel>>> get() = _moviesResource

    suspend fun getMoviesResource(){
        _moviesResource.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _moviesResource.postValue(movieRepository.getMovies())
        }
    }

    fun getImageFullPath(path: String): String {
        return movieRepository.getImageFullPath(path)
    }

    suspend fun saveMovie(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.saveMovie(item)
            val data = _moviesResource.value?.data?.map {
                if(item.id == it.id) {
                    it.saved = true
                }
                it
            }
            _moviesResource.postValue(Resource.Success(data))
        }
    }
}