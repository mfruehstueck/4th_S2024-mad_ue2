package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.navigation.BaseScreen
import com.example.movieappmad24.R
import com.example.movieappmad24.Screens
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val movieList = moviesViewModel.movielist

    BaseScreen(
        title = "Movie App",
        content = { innerPadding ->
            MovieList(
                movieList = movieList,
                navController = navController,
                innerPadding = innerPadding as PaddingValues
            )
        },
        navController = navController
    )
}

@Composable
fun MovieDetails(movie: Movie) {
    Column {
        Text(text = "Director: ${movie.director}")
        Text(text = "Released: ${movie.year}")
        Text(text = "Genre: ${movie.genre}")
        Text(text = "Actors: ${movie.actors}")
        Text(text = "Rating: ${movie.rating}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Plot: ${movie.plot}")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieList(movieList: List<Movie>, navController: NavController, innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.consumeWindowInsets(innerPadding),
        contentPadding = innerPadding
    ) {
        items(movieList) { movie ->
            MovieRow(movie) { movieID ->
                navController.navigate("${Screens.detailscreen}/$movieID")
                //movieID -> Log.d("MovieList", "My callback value: $movieID")
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    var isExpanded by remember { mutableStateOf(false) }
    var isWatchlisted by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
//            .clickable { isExpanded = !isExpanded }, //N: from exercise-02
            .clickable { onItemClick(movie.id) },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.movie_image)
                )

                IconButton(onClick = { isWatchlisted = !isWatchlisted }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = if(isWatchlisted) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like!"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title)
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        modifier = Modifier,
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "showDetails"
                    )
                }
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                MovieDetails(movie = movie)
            }
        }
    }
}
