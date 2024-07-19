package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class SavedTvShowsRepository(
    private val tvShowDao: TvShowDao,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
): SavedItemsRepositoryInterface {
    override suspend fun getSavedItems(): List<ItemModel> {
        return tvShowDao.getTvShows().map {
            ItemModel(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.coverPath,
                true,
                false
            )
        }
    }

    override suspend fun deleteItem(item: ItemModel) {
        tvShowDao.deleteTvShow(
            EntityTvShow(
                item.id,
                item.title,
                item.overview,
                item.posterPath,
                item.coverPath
            )
        )
    }

    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }
}