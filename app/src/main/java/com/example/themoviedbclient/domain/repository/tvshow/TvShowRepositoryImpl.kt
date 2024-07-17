package com.example.themoviedbclient.domain.repository.tvshow

import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.data.dto.tvshow.TvShowsDTO
import com.example.themoviedbclient.data.model.ItemModel
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource,
    private val tvShowDao: TvShowDao
): TvShowRepository {
    override suspend fun getTvShows(timeWindow: String, language: String): Resource<List<ItemModel>> {
        return responseToResource(tvShowRemoteDataSource.getTvShows(timeWindow,language))
    }

    override suspend fun getSavedTvShows(): List<ItemModel> {
        return tvShowDao.getTvShows().map {
            ItemModel(it.id,it.title,it.overview,it.posterPath,it.coverPath)
        }
    }

    override suspend fun saveTvShow(item: ItemModel) {
        val entity = EntityTvShow(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        tvShowDao.insertTvShow(entity)
    }

    override suspend fun deleteTvShow(item: ItemModel) {
        val entity = EntityTvShow(item.id,item.title,item.overview,item.posterPath,item.coverPath)
        tvShowDao.deleteTvShow(entity)
    }

    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }

    private fun responseToResource(response: Response<TvShowsDTO>): Resource<List<ItemModel>> {
        if(response.isSuccessful) {
            val result = response.body()?.result.orEmpty().map {
                ItemModel( it.id, it.title, it.overview, it.posterPath, it.coverPath)
            }
            return Resource.Success(result)
        }
        else return Resource.Error(response.message())
    }
}