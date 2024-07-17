package com.example.themoviedbclient.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviedbclient.data.datasource.local.LocalDatabase
import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    fun  provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java, "tmdb_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(localDatabase: LocalDatabase): MovieDao {
        return  localDatabase.movieDao()
    }

    @Provides
    fun provideTvShowDao(localDatabase: LocalDatabase): TvShowDao {
        return  localDatabase.tvShowDao()
    }
}