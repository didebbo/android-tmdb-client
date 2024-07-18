package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.dto.movie.MoviesDTO
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class MoviesRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val movieDao: MovieDao
): ItemsRepository {
    override suspend fun getItems(): Resource<List<ItemModel>> {
        return  responseToResource(movieRemoteDataSource.getMovies())
    }

    override suspend fun getSavedItems(): List<ItemModel> {
        return movieDao.getMovies().map {
            ItemModel(it.id,it.title,it.overview,it.posterPath,it.coverPath,true)
        }
    }

    override suspend fun saveItem(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.insertMovie(entity)
    }

    override suspend fun deleteItem(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.deleteMovie(entity)
    }

    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    private suspend fun responseToResource(response: Response<MoviesDTO>): Resource<List<ItemModel>> {
        if(response.isSuccessful) {
            val savedMovies = getSavedItems().map { it.id }.toSet()
            val result = response.body()?.result.orEmpty().map { dto ->
                ItemModel( dto.id, dto.title, dto.overview, dto.posterPath, dto.coverPath,dto.id in savedMovies)
            }
            return Resource.Success(result)
        }
        else return Resource.Error(response.message())
    }
}