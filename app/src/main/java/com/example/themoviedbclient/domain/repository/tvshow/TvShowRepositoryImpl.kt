package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): TvShowRepository {
    override suspend fun getTvShows(timeWindow: String, language: String): Resource<List<ItemModel>> {
        return responseToResource(tvShowRemoteDataSource.getTvShows(timeWindow,language))
    }

    override fun getPosterFulPath(imageFile: String): String {
        return imagePathRemoteDataSource.getImageFullPath(imageFile)
    }

    private fun responseToResource(response: Response<TvShowsDTO>): Resource<List<ItemModel>> {
        if(response.isSuccessful) {
            val result = response.body()?.result.orEmpty().map {
                val posterURL = imagePathRemoteDataSource.getImageFullPath(it.posterPath)
                val coverURL = imagePathRemoteDataSource.getImageFullPath(it.backdropPath)
                ItemModel( it.id, it.title, it.overview, posterURL, coverURL)
            }
            return Resource.Success(result)
        }
        else return Resource.Error(response.message())
    }
}