package com.example.movieappmad24.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    fun add(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * FROM movie WHERE movie.dbID=:dbID")
    fun getByID(dbID: Long): Flow<Movie?>

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE movie.inWatchlist=1")
    fun getWatchlist(): Flow<List<Movie>>
}