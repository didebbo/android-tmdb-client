package com.example.themoviedbclient.data.datasource.remote.tvshow

import com.example.themoviedbclient.data.api.tvshow.TvShowApiService
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import retrofit2.Response

class TvShowRemoteDataSource(
    private val tvShowApiService: TvShowApiService
): TvShowRemoteDataSourceInterface {
    override suspend fun getTvShows(): Response<TvShowsDTO> {
        return tvShowApiService.getTvShows()
    }
}