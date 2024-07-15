package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.MovieApiService
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSourceImpl
import com.example.themoviedbclient.domain.repository.movie.MovieRepository
import com.example.themoviedbclient.domain.repository.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MovieModule {
    @Provides
    fun provideMovieRepository(movieRemoteDatasource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDatasource)
    }

    @Provides
    fun provideMovieRemoteDatasource(movieApiService: MovieApiService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(movieApiService)
    }

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}