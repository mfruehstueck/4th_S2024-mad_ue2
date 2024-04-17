package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.navigation.BaseScreen
import com.example.movieappmad24.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WatchlistScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val watchlistMovies = moviesViewModel.watchlist

    BaseScreen(
        title = "Watchlist",
        content = { innerPadding ->
            MovieList(
                movieList = watchlistMovies,
                navController = navController,
                moviesViewModel = moviesViewModel,
                innerPadding = innerPadding as PaddingValues
            )
        },
        navController = navController
    )
}