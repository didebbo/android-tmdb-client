package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ImagePathModule {
    @Provides
    fun providePosterFullPath(): ImagePathRemoteDataSource {
        return ImagePathRemoteDataSourceImpl()
    }
}