package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.R
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.BaseScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DetailScreen(navController: NavController, movieID: String?) {
    val currentMovie = getMovies().find { movie -> movie.id == movieID }

    currentMovie?.let {
        BaseScreen(title = it.title,
            navController = navController,
            content = { innerPadding ->
                Text("This is movie ${currentMovie.id}")
                Column(
                    modifier = Modifier.padding(innerPadding as PaddingValues)
                ) {
                    MovieRow(movie = currentMovie)
                    LazyRow {
                        items(currentMovie.images) { image ->
                            AsyncImage(
                                model = image,
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                placeholder = painterResource(R.drawable.movie_image)
                            )
                        }
                    }
                }
            }
        )
    }

//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("${currentMovie?.title}") },
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
//        bottomBar = {},
//        content = { innerPadding ->
//            Text("This is movie ${currentMovie?.id}")
//            if (currentMovie != null) {
//                Column(
//                    modifier = Modifier.padding(innerPadding)
//                ) {
//                    MovieRow(movie = currentMovie)
//                    LazyRow {
//                        items(currentMovie.images) { image ->
//                            AsyncImage(
//                                model = image,
//                                contentScale = ContentScale.Crop,
//                                contentDescription = null,
//                                placeholder = painterResource(R.drawable.movie_image)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    )
}