package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
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
import coil.compose.AsyncImage
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieApp()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MovieApp() {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Movie App") }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        onClick = { /*TODO*/ },
                        icon = { Icon(Icons.Filled.Home, contentDescription = "") },
                        label = { Text(text = "Home") })
                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = { Icon(Icons.Filled.Star, contentDescription = "") },
                        label = { Text(text = "Wishlist") })
                }
            },
            content = { innerPadding ->
                MovieList(movieList = getMovies(), innerPadding = innerPadding)
            }
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
    fun MovieList(movieList: List<Movie>, innerPadding: PaddingValues) {
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            contentPadding = innerPadding
        ) {
            items(movieList) { movie ->
                MovieRow(movie)
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun MovieRow(movie: Movie) {
        var isExpanded by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable { isExpanded = !isExpanded },
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

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.FavoriteBorder,
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
}
