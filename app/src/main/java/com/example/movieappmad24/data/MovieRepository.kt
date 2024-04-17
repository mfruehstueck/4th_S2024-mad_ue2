package com.example.movieappmad24.data

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieDAO
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDAO: MovieDAO) {
    suspend fun add(movie: Movie) = movieDAO.add(movie)
    suspend fun update(movie: Movie) = movieDAO.update(movie)
    suspend fun delete(movie: Movie) = movieDAO.delete(movie)
    fun getByID(dbID: Long): Flow<Movie?> = movieDAO.getByID(dbID)
    fun getAll(): Flow<List<Movie>> = movieDAO.getAll()
    fun getWatchlist(): Flow<List<Movie>> = movieDAO.getWatchlist()
}