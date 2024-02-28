package com.example.movieappmad24

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    private val movieList = mutableListOf<String>("Avatar", "Bvatar", "Cvatar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    MovieList(movies = movieList)
//                    Card {
//                        Image(painter = painterResource(id = R.drawable.movie_image), contentDescription = "placeholder_image")
//                    }
                }
            }
        }
    }

    @Composable
    fun MovieRowMy() {
        Column {
            Box {
                Card {
                    Image(painter = painterResource(id = R.drawable.movie_image), contentDescription = "placeholder_image")
                }
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Like!")
            }
            Row {
                Text("Avatar")
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
            }
        }
    }

    @Composable
    fun MovieList(movies: List<String>){
        LazyColumn {
            items(movies) {movie -> 
                MovieRow(movie = movie)
            }
        }
    }

    @Composable
    fun MovieRow(movie: String) {
        Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                shape = ShapeDefaults.Large,
                elevation = CardDefaults.cardElevation(10.dp)) {
            Column {
                Box(
                        modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd,
                ) {
                    Image(
                            painter = painterResource(id = R.drawable.movie_image),
                            contentScale = ContentScale.Crop,
                            contentDescription = "placeholder image"
                    )

                    Icon(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like!"
                    )
                }
                Row(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = movie)
                    Icon(modifier = Modifier,
                            imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "showDetails")
                }
            }
        }
    }
}