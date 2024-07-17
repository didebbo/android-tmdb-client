package com.example.themoviedbclient.presentation.viewmodel.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.movie.Movie
import com.example.themoviedbclient.data.model.movie.Movies
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

    private var _moviesResource: MutableLiveData<Resource<Movies>> =
        MutableLiveData(Resource.Null())

    val moviesResource: LiveData<Resource<Movies>> get() = _moviesResource

    suspend fun getMoviesResource(){
        _moviesResource.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _moviesResource.postValue(movieRepository.getMovies("day", "en-US"))
        }
    }

    fun getPosterFullPathFrom(movie: Movie): String {
        return movieRepository.getPosterFulPath(movie.posterPath)
    }



}