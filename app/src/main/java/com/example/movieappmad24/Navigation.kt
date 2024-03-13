package com.example.movieappmad24.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.RoutArguments
import com.example.movieappmad24.Screens
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation(startup: Screens) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "$startup") {
        composable(route = "${Screens.homescreen}") {
            com.example.movieappmad24.screens.HomeScreen(navController)
        }
        composable(
            "${Screens.detailscreen}/{${RoutArguments.movieID}}",
            arguments = listOf(navArgument(name = "${RoutArguments.movieID}") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            com.example.movieappmad24.screens.DetailScreen(
                navController,
                movieID = backStackEntry.arguments?.getString("${RoutArguments.movieID}")
            )
        }
        composable("${Screens.watchlistscreen}") {
            WatchlistScreen(navController = navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseNavigation(
    title: String,
    content: @Composable (Any?) -> Unit = {},
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("${Screens.homescreen}") },
                    icon = { Icon(Icons.Filled.Home, contentDescription = "") },
                    label = { Text(text = "Home") })
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("${Screens.watchlistscreen}") },
                    icon = { Icon(Icons.Filled.Star, contentDescription = "") },
                    label = { Text(text = "Wishlist") })
            }
        },
        content = { innerPadding -> content(innerPadding) }
    )
}