package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): MovieRepository {
    override suspend fun getMovies(
        timeWindow: String,
        language: String
    ): Resource<List<ItemModel>> {
        return  responseToResource(movieRemoteDataSource.getMovies(timeWindow,language))
    }

    override fun getPosterFulPath(imageFile: String): String {
        return imagePathRemoteDataSource.getImageFullPath(imageFile)
    }

    private fun responseToResource(response: Response<MoviesDTO>): Resource<List<ItemModel>> {
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