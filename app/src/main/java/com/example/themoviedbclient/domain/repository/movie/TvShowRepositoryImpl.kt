package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.data.model.tvshow.TvShows
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): TvShowRepository {
    override suspend fun getTvShows(timeWindow: String, language: String): Resource<TvShows> {
        return responseToResource(tvShowRemoteDataSource.getTrendingTvShows(timeWindow,language))
    }

    override fun getPosterFulPath(imageFile: String): String {
        return imagePathRemoteDataSource.getPosterFullPath(imageFile)
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if(response.isSuccessful) return Resource.Success(response.body())
        else return Resource.Error(response.message())
    }
}