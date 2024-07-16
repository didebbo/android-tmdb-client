package com.example.themoviedbclient.data.datasource.remote.image

import com.example.themoviedbclient.data.model.TrendingMovies
import com.example.themoviedbclient.data.util.Resource
import retrofit2.Response

interface ImagePathRemoteDataSource {
    fun getPosterFullPath(imageFile: String): String
}