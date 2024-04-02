package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.BaseScreen
import com.example.movieappmad24.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val watchlistMovies = moviesViewModel.watchlist

    BaseScreen(
        title = "Watchlist",
        content = { innerPadding ->
            MovieList(
                movieList = watchlistMovies,
                navController = navController,
                innerPadding = innerPadding as PaddingValues
            )
        },
        navController = navController
    )
}