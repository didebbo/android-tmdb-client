package com.example.themoviedbclient.data.datasource.remote.tvshow

import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import retrofit2.Response

interface TvShowRemoteDataSourceInterface {
    suspend fun getTvShows(): Response<TvShowsDTO>
}