package com.example.themoviedbclient.data.datasource.remote.tvshow

import com.example.themoviedbclient.data.api.TvShowApiService
import com.example.themoviedbclient.data.model.tvshow.TvShows
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tvShowApiService: TvShowApiService
): TvShowRemoteDataSource {
    override suspend fun getTrendingTvShows(timeWindow: String, language: String): Response<TvShows> {
        return tvShowApiService.getTrendingTvShows(timeWindow,language)
    }
}