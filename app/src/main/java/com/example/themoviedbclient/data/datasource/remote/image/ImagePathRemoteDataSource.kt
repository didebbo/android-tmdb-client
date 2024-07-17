package com.example.themoviedbclient.data.datasource.remote.image

interface ImagePathRemoteDataSource {
    fun getImageFullPath(imageFile: String): String
}