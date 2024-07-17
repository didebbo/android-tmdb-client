package com.example.themoviedbclient.domain.repository.movie

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val movieDao: MovieDao
): MovieRepository {
    override suspend fun getMovies(
        timeWindow: String,
        language: String
    ): Resource<List<ItemModel>> {
        return  responseToResource(movieRemoteDataSource.getMovies(timeWindow,language))
    }

    override suspend fun getSavedMovies(): List<ItemModel> {
        return movieDao.getMovies().map {
            ItemModel(it.id,it.title,it.overview,it.posterPath,it.coverPath)
        }
    }

    override suspend fun saveMovie(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.insertMovie(entity)
    }

    override suspend fun deleteMovie(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.deleteMovie(entity)
    }

    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    private fun responseToResource(response: Response<MoviesDTO>): Resource<List<ItemModel>> {
        if(response.isSuccessful) {
            val result = response.body()?.result.orEmpty().map {
                ItemModel( it.id, it.title, it.overview, it.posterPath, it.coverPath)
            }
            return Resource.Success(result)
        }
        else return Resource.Error(response.message())
    }
}