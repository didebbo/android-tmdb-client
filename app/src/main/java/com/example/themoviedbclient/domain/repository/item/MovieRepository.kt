package com.example.themoviedbclient.domain.repository.item

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class MovieRepository(
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val movieDao: MovieDao
): ItemRepository {
    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    override suspend fun saveItem(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.insertMovie(entity)
    }
}