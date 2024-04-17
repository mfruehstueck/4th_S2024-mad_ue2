package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.R
import com.example.movieappmad24.navigation.BaseScreen
import com.example.movieappmad24.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailScreen(navController: NavController, movieID: String?, moviesViewModel: MoviesViewModel) {
    val currentMovie = movieID?.let { moviesViewModel.getMovieByID(it) }

    currentMovie?.let {
        BaseScreen(title = it.title,
            navController = navController,
            content = { innerPadding ->
                Text("This is movie ${currentMovie.id}")
                Column(
                    modifier = Modifier.padding(innerPadding as PaddingValues)
                ) {
                    MovieRow(movie = currentMovie, moviesViewModel = moviesViewModel, navController)
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
                    VideoPlayer(trailerName = currentMovie.trailer)
                }
            }
        )
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun VideoPlayer(trailerName: String){
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                "android.resource://${context.packageName}/${context.resources.getIdentifier(trailerName, "raw", context.packageName)}"
            ))
            prepare()
            playWhenReady = true
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        factory = {
            PlayerView(context).also { playerView ->
                playerView.player = exoPlayer
            }
        },
        update = {
            when(lifecycle) {
                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                    //it.player?.play()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }
                else -> Unit
            }
        }
    )
}