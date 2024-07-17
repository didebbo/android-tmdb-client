package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.model.movie.Movies
import com.example.themoviedbclient.data.model.tvshow.TvShows
import com.example.themoviedbclient.data.util.Resource

interface TvShowRepository {

    suspend fun getTvShows(timeWindow: String, language: String): Resource<TvShows>

    fun getPosterFulPath(imageFile: String): String
}