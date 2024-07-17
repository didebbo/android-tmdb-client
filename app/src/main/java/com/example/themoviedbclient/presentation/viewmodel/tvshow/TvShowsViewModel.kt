package com.example.themoviedbclient.presentation.viewmodel.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.model.movie.Movie
import com.example.themoviedbclient.data.model.tvshow.TvShow
import com.example.themoviedbclient.data.model.tvshow.TvShows
import com.example.themoviedbclient.data.util.Resource
import com.example.themoviedbclient.domain.repository.tvshow.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    private var _tvShowsResource: MutableLiveData<Resource<TvShows>> =
        MutableLiveData(Resource.Null())

    val tvShowsResource: LiveData<Resource<TvShows>> get() = _tvShowsResource

    suspend fun getTvShowsResource(){
        _tvShowsResource.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _tvShowsResource.postValue(tvShowRepository.getTvShows("day", "en-US"))
        }
    }

    fun getPosterFullPathFrom(tvShow: TvShow): String {
        return tvShowRepository.getPosterFulPath(tvShow.posterPath)
    }
}