package com.example.moviesapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.moviesapp.domain.models.Movie
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun Home(viewModel: HomeViewModel) {
    viewModel.getOnPlayingMovies()
    viewModel.getPopularMovies()

    val viewState by viewModel.viewState.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies App") },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 8.dp
            )
        },
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight(),
        ) {
            ListOnPlayingMovies(viewState.onPlayingMovies)
            ListPopularMovies(viewState.popularMovies)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun ListOnPlayingMovies(movies: List<Movie>) {
    if (movies.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            val pagerState = rememberPagerState(pageCount = movies.size)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                CardMovieOnPLaying(pageOffset = pageOffset, movie = movies[page], onClick = {})
            }
        }
    }
}


@Composable
fun CardMovieOnPLaying(pageOffset: Float, movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .graphicsLayer {
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {
        CardMovie(movie = movie, height = 400.dp, onClick = onClick)
    }
}

@Composable
fun ListPopularMovies(movies: List<Movie>) {
    Column {
        Text(
            text = "Popular movies",
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.h6
        )
        LazyRow {
            itemsIndexed(movies) { index, movie ->
                if (index == 0) Spacer(modifier = Modifier.width(8.dp))
                CardMovie(movie = movie, showTitle = true, height = 200.dp, onClick = {})
            }
        }
    }
}

@Composable
fun CardMovie(movie: Movie, showTitle: Boolean = false, height: Dp, onClick: () -> Unit) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .clickable(onClick = onClick)
                .height(height),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Image(
                painter = rememberCoilPainter(movie.imagePoster, fadeIn = true),
                contentDescription = movie.title,
                contentScale = if (showTitle) ContentScale.Fit else ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        if (showTitle) {
            Text(
                text = movie.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = typography.subtitle1,
                modifier = Modifier.width(120.dp)
            )
        }
    }
}
