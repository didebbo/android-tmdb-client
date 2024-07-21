package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.domain.repository.item.MovieRepository
import com.example.themoviedbclient.domain.repository.item.TvShowRepository
import com.example.themoviedbclient.domain.repository.items.MoviesRepository
import com.example.themoviedbclient.domain.repository.items.SavedMoviesRepository
import com.example.themoviedbclient.domain.repository.items.SavedTvShowsRepository
import com.example.themoviedbclient.domain.repository.items.TvShowsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideMoviesRepository(
        movieRemoteDatasource: MovieRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao,
        gson: Gson
    ): MoviesRepository {
        return MoviesRepository(movieRemoteDatasource, imagePathRemoteDataSource,movieDao,gson)
    }

    @Provides
    fun provideTvShowsRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao,
        gson: Gson
    ): TvShowsRepository {
        return TvShowsRepository(tvShowRemoteDataSource,imagePathRemoteDataSource,tvShowDao,gson)
    }

    @Provides
    fun provideMovieRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepository(imagePathRemoteDataSource,movieDao)
    }

    @Provides
    fun provideTvShowRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao
    ): TvShowRepository {
        return TvShowRepository(imagePathRemoteDataSource,tvShowDao)
    }

    @Provides
    fun provideSavedMoviesRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): SavedMoviesRepository {
        return SavedMoviesRepository(movieDao, imagePathRemoteDataSource)
    }

    @Provides
    fun provideSavedTvShowsRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao
    ): SavedTvShowsRepository {
        return SavedTvShowsRepository(tvShowDao,imagePathRemoteDataSource)
    }
}