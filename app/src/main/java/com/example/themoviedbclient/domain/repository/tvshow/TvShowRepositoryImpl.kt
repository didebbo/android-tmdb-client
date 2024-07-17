package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): TvShowRepository {
    override suspend fun getTvShows(timeWindow: String, language: String): Resource<TvShowsDTO> {
        return responseToResource(tvShowRemoteDataSource.getTvShows(timeWindow,language))
    }

    override fun getPosterFulPath(imageFile: String): String {
        return imagePathRemoteDataSource.getImageFullPath(imageFile)
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if(response.isSuccessful) return Resource.Success(response.body())
        else return Resource.Error(response.message())
    }
}