package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class SavedItemRepository(
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
): SavedItemsRepositoryInterface {
    override suspend fun getSavedMovies(): List<ItemModel> {
        return movieDao.getMovies().map {
            ItemModel(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.coverPath,
                true
            )
        }
    }

    override suspend fun getSavedTvShows(): List<ItemModel> {
        return tvShowDao.getTvShows().map {
            ItemModel(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.coverPath,
                true
            )
        }
    }

    override suspend fun deleteMovie(item: ItemModel) {
        movieDao.deleteMovie(
            EntityMovie(
                item.id,
                item.title,
                item.overview,
                item.posterPath,
                item.coverPath
            )
        )
    }

    override suspend fun deleteTvShow(item: ItemModel) {
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