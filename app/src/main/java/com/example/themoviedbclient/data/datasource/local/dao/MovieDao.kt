package com.example.themoviedbclient.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedbclient.data.datasource.local.entity.EntityMovie
import com.example.themoviedbclient.data.dto.movie.MovieDTO

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<EntityMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(users: EntityMovie)

    @Delete
    suspend fun deleteMovie(user: EntityMovie)
}