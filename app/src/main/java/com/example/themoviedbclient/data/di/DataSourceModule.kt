package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.movie.MovieApiService
import com.example.themoviedbclient.data.api.tvshow.TvShowApiService
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideMovieRemoteDatasource(movieApiService: MovieApiService): MovieRemoteDataSource {
        return MovieRemoteDataSource(movieApiService)
    }

    @Provides
    fun provideTvShowRemoteDataSource(tvShowApiService: TvShowApiService): TvShowRemoteDataSource {
        return TvShowRemoteDataSource(tvShowApiService)
    }

    @Provides
    fun providePosterFullPath(): ImagePathRemoteDataSource {
        return ImagePathRemoteDataSource()
    }
}