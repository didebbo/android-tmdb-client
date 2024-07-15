package com.example.themoviedbclient.data.api

import com.example.themoviedbclient.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient =  OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    private val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val  movieApiService: MovieApiService by lazy {
        client.create(MovieApiService::class.java)
    }
}