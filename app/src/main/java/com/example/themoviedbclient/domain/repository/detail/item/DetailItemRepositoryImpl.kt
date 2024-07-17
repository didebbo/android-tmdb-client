package com.example.themoviedbclient.domain.repository.detail.item

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource

class DetailItemRepositoryImpl(
    private val imagePathRemoteDataSource: ImagePathRemoteDataSource
): DetailItemRepository {
    override fun getImageFullPath(path: String): String {
        return imagePathRemoteDataSource.getImageFullPath(path)
    }
}