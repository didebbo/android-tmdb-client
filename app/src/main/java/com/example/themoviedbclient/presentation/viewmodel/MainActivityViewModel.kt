package com.example.themoviedbclient.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.MovieList
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private var _movieList: MutableLiveData<Resource<MovieList>> = MutableLiveData()
    val movieList: LiveData<Resource<MovieList>> get() = _movieList
    suspend fun fetchTrendingMovies(){
       viewModelScope.launch {
           _movieList.value = movieRepository.getTrendingMovies("day", "en-US")
       }
    }
}