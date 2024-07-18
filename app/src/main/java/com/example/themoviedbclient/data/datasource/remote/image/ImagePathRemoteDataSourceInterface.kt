package com.example.themoviedbclient.data.datasource.remote.image

interface ImagePathRemoteDataSourceInterface {
    fun getImageFullPath(imageFile: String): String
}