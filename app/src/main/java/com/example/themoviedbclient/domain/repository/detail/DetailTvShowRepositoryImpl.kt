package com.example.themoviedbclient.domain.repository.detail

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class DetailTvShowRepositoryImpl(
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val tvShowDao: TvShowDao
): DetailItemRepository {
    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    override suspend fun saveItem(item: ItemModel) {
        val entity = EntityTvShow(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        tvShowDao.insertTvShow(entity)
    }
}