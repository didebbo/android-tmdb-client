package com.example.themoviedbclient.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.datasource.local.entity.EntityTvShow
import com.example.themoviedbclient.data.dto.movie.MovieDTO

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show")
    suspend fun getTvShows(): List<EntityTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(users: EntityTvShow)

    @Delete
    suspend fun deleteTvShow(user: EntityTvShow)
}