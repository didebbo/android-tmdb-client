package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import com.example.themoviedbclient.data.util.Resource

interface TvShowRepository {

    suspend fun getTvShows(timeWindow: String, language: String): Resource<TvShowsDTO>

    fun getPosterFulPath(imageFile: String): String
}