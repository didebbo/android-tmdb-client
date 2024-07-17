package com.example.themoviedbclient.data.datasource.remote.image

interface ImagePathRemoteDataSource {
    fun getPosterFullPath(imageFile: String): String
}