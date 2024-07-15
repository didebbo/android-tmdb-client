package com.example.themoviedbclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.Movie
import com.example.themoviedbclient.data.model.TrendingMovies
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private var _trendingMoviesResource: MutableLiveData<Resource<TrendingMovies>> = MutableLiveData(Resource.Null())
    val trendingMoviesResource: LiveData<Resource<TrendingMovies>> get() = _trendingMoviesResource
    suspend fun fetchTrendingMoviesResource(){
        _trendingMoviesResource.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _trendingMoviesResource.postValue(movieRepository.getTrendingMovies("day", "en-US"))
        }
    }

}