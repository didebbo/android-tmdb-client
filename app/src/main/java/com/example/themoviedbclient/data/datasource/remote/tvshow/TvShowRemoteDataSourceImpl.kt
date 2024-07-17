package com.example.themoviedbclient.data.datasource.remote.tvshow

import com.example.themoviedbclient.data.api.tvshow.TvShowApiService
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tvShowApiService: TvShowApiService
): TvShowRemoteDataSource {
    override suspend fun getTvShows(timeWindow: String, language: String): Response<TvShowsDTO> {
        return tvShowApiService.getTvShows(timeWindow,language)
    }
}