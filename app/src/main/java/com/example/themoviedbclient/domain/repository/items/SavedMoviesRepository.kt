package com.example.themoviedbclient.domain.repository.items

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.model.ItemModel

class SavedMoviesRepository(
    private val movieDao: MovieDao,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
): SavedItemsRepositoryInterface {
    override suspend fun getSavedItems(): List<ItemModel> {
        return movieDao.getMovies().map {
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

    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }
}