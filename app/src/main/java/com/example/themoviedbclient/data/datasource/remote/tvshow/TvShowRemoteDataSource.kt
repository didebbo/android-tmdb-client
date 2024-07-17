package com.example.themoviedbclient.data.datasource.remote.tvshow

import com.example.themoviedbclient.data.model.tvshow.TvShows
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTrendingTvShows(timeWindow: String, language: String): Response<TvShows>
}