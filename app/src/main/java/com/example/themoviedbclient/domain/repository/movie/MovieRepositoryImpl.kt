package com.example.themoviedbclient.domain.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.TrendingMovies
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): MovieRepository {
    override suspend fun getTrendingMovies(
        timeWindow: String,
        language: String
    ): Resource<TrendingMovies> {
        return responseToResource(movieRemoteDataSource.getTrendingMovies(timeWindow,language))
    }

    override fun getPosterFulPath(imageFile: String): String {
        return imagePathRemoteDataSource.getPosterFullPath(imageFile)
    }

    private fun <T> responseToResource(response: Response<T>): Resource<T> {
        if(response.isSuccessful) return Resource.Success(response.body())
        else return Resource.Error(response.message())
    }
}