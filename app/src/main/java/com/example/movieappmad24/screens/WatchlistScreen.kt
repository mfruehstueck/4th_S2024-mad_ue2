package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.BaseNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(navController: NavController) {
    val watchlistMovies = listOfNotNull(getMovies().find { movie -> movie.inWatchlist })

    BaseNavigation(
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
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("Watchlist") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigateUp() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                }
//            )
//        },
//        bottomBar = {
//            NavigationBar {
//                NavigationBarItem(
//                    selected = true,
//                    onClick = { navController.navigate("${Screens.homescreen}") },
//                    icon = { Icon(Icons.Filled.Home, contentDescription = "") },
//                    label = { Text(text = "Home") })
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("${Screens.watchlistscreen}") },
//                    icon = { Icon(Icons.Filled.Star, contentDescription = "") },
//                    label = { Text(text = "Wishlist") })
//            }
//        },
//        content = {}
//    )
}