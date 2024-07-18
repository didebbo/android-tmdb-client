package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.movie.MovieApiService
import com.example.themoviedbclient.data.api.tvshow.TvShowApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    fun provideTvShowApiService(retrofit: Retrofit): TvShowApiService {
        return retrofit.create(TvShowApiService::class.java)
    }
}