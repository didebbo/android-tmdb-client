package com.example.themoviedbclient.domain.repository.detail

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class DetailMovieRepositoryImpl(
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val movieDao: MovieDao
): DetailItemRepository {
    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    override suspend fun saveItem(item: ItemModel) {
        val entity = EntityMovie(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        movieDao.insertMovie(entity)
    }
}