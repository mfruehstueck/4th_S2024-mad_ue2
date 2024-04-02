package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

class MoviesViewModel : ViewModel() {
    private val _movielist = getMovies().toMutableList()

    val movielist: List<Movie> = _movielist
    val watchlist: List<Movie> = _movielist.filter { movie -> movie.inWatchlist }

    fun toggleWatchlist(movieID: String) = _movielist.find { it.id == movieID }?.let { movie: Movie -> movie.inWatchlist = !movie.inWatchlist }
}