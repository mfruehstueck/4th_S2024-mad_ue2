package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

class MoviesViewModel : ViewModel() {
    private val _movielist = getMovies().toMutableList()
    private val _watchlist = _movielist.filter { movie -> movie.inWatchlist }.toMutableList()

    val movielist: List<Movie> = _movielist
    val watchlist: List<Movie> = _watchlist

    fun toggleWatchlist(movie: Movie): Boolean {
        movie.inWatchlist = !movie.inWatchlist
        toggleFavorite(movie)
        return movie.inWatchlist
    }

    fun getMovieByID(movieID: String): Movie? {
        return movielist.find { movie -> movie.id == movieID }
    }


    fun getisFavorite(movie: Movie): Boolean {
        return movie.inWatchlist;
    }


    private fun toggleFavorite(movie: Movie) {
        if (_watchlist.contains(movie) && !movie.inWatchlist)
            _watchlist.remove(movie)
        else
            _watchlist.add(movie)
    }

    fun getFavorite(): List<Movie> {
        return watchlist
    }
}