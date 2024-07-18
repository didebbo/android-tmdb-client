package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.movie.MovieApiService
import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSourceImpl
import com.example.themoviedbclient.domain.repository.item.MovieRepository
import com.example.themoviedbclient.domain.repository.items.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object MovieModule {
    @Provides
    fun provideMoviesRepository(
        movieRemoteDatasource: MovieRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): MoviesRepository {
        return MoviesRepository(movieRemoteDatasource, imagePathRemoteDataSource,movieDao)
    }

    @Provides
    fun provideMovieRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepository(imagePathRemoteDataSource,movieDao)
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