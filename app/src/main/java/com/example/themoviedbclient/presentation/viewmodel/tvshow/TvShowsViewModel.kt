package com.example.themoviedbclient.presentation.viewmodel.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbclient.data.dto.tvshow.TvShowDTO
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
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

    private var _tvShowsDTOResource: MutableLiveData<Resource<TvShowsDTO>> =
        MutableLiveData(Resource.Null())

    val tvShowsDTOResource: LiveData<Resource<TvShowsDTO>> get() = _tvShowsDTOResource

    suspend fun getTvShowsResource(){
        _tvShowsDTOResource.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _tvShowsDTOResource.postValue(tvShowRepository.getTvShows("day", "en-US"))
        }
    }

    fun getPosterFullPathFrom(tvShowDTO: TvShowDTO): String {
        return tvShowRepository.getPosterFulPath(tvShowDTO.posterPath)
    }
}