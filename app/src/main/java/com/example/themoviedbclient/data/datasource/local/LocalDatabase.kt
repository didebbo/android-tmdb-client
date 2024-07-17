package com.example.themoviedbclient.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themoviedbclient.data.datasource.local.dao.MovieDao
import com.example.themoviedbclient.data.datasource.local.dao.TvShowDao
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        EntityMovie::class,
        EntityTvShow::class
    ]
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao
}