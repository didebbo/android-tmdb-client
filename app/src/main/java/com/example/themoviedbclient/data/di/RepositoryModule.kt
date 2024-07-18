package com.example.themoviedbclient.data.di

import com.example.themoviedbclient.data.api.movie.MovieApiService
import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.remote.image.ImagePathRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.movie.MovieRemoteDataSource
import com.example.themoviedbclient.data.datasource.remote.tvshow.TvShowRemoteDataSource
import com.example.themoviedbclient.domain.repository.item.MovieRepository
import com.example.themoviedbclient.domain.repository.item.TvShowRepository
import com.example.themoviedbclient.domain.repository.items.MoviesRepository
import com.example.themoviedbclient.domain.repository.items.SavedItemRepository
import com.example.themoviedbclient.domain.repository.items.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideMoviesRepository(
        movieRemoteDatasource: MovieRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao
    ): MoviesRepository {
        return MoviesRepository(movieRemoteDatasource, imagePathRemoteDataSource,movieDao)
    }

    @Provides
    fun provideTvShowsRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        tvShowDao: TvShowDao
    ): TvShowsRepository {
        return TvShowsRepository(tvShowRemoteDataSource,imagePathRemoteDataSource,tvShowDao)
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
    fun provideSavedItemRepository(
        imagePathRemoteDataSource: ImagePathRemoteDataSource,
        movieDao: MovieDao,
        tvShowDao: TvShowDao
    ): SavedItemRepository {
        return SavedItemRepository(imagePathRemoteDataSource,movieDao,tvShowDao)
    }
}