package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.tvshow.TvShowApiService
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSourceImpl
import com.example.themoviedbclient.domain.repository.tvshow.TvShowRepository
import com.example.themoviedbclient.domain.repository.tvshow.TvShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object TvShowModule {

    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao
    ): TvShowRepository {
        return TvShowRepositoryImpl(tvShowRemoteDataSource,imagePathRemoteDataSource,tvShowDao)
    }

    @Provides
    fun provideTvShowRemoteDataSource(tvShowApiService: TvShowApiService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tvShowApiService)
    }

    @Provides
    fun provideTvShowApiService(retrofit: Retrofit): TvShowApiService {
        return retrofit.create(TvShowApiService::class.java)
    }
}