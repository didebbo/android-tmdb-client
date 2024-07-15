package com.example.themoviedbclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.TrendingMovies
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private var _trendingMovies: MutableLiveData<Resource<TrendingMovies>> = MutableLiveData()
    val trendingMovies: LiveData<Resource<TrendingMovies>> get() = _trendingMovies
    suspend fun fetchTrendingMovies(){
       viewModelScope.launch {
           _trendingMovies.value = movieRepository.getTrendingMovies("day", "en-US")
       }
    }
}