package com.example.themoviedbclient.data.datasource.remote.image

import com.example.themoviedbclient.BuildConfig

class ImagePathRemoteDataSourceImpl: ImagePathRemoteDataSource {
    override fun getImageFullPath(imageFile: String): String {
        return "${BuildConfig.TMDB_IMAGES_URL}t/p/original$imageFile"
    }
}