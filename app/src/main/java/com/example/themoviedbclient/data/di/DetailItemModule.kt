package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.domain.repository.detail.item.DetailItemRepository
import com.example.themoviedbclient.domain.repository.detail.item.DetailItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailItemModule {
    @Provides
    fun provideDetailItemRepository(imagePathRemoteDataSource: ImagePathRemoteDataSource): DetailItemRepository {
        return  DetailItemRepositoryImpl(imagePathRemoteDataSource)
    }
}