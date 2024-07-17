package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.model.tvshow.TvShows
import com.example.themoviedbclient.data.util.Resource

interface TvShowRepository {

    suspend fun getTvShows(timeWindow: String, language: String): Resource<TvShows>

    fun getPosterFulPath(imageFile: String): String
}