package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.domain.repository.detail.DetailItemRepository
import com.example.themoviedbclient.domain.repository.detail.DetailMovieRepositoryImpl
import com.example.themoviedbclient.domain.repository.detail.DetailTvShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailItemModule {
    @Provides
    fun provideDetailMovieRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): DetailMovieRepositoryImpl {
        return  DetailMovieRepositoryImpl(
            imagePathRemoteDataSource,
            movieDao
        )
    }

    @Provides
    fun provideDetailTvShowRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao
    ): DetailTvShowRepositoryImpl {
        return  DetailTvShowRepositoryImpl(
            imagePathRemoteDataSource,
            tvShowDao
        )
    }
}