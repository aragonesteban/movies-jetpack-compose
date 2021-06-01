package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesapp.data.remote.movies.RemoteMoviesImpl
import com.example.moviesapp.data.remote.retrofit.buildRetrofit
import com.example.moviesapp.data.remote.retrofit.createMoviesApi
import com.example.moviesapp.domain.repository.MoviesRepositoryImpl
import com.example.moviesapp.ui.home.Home
import com.example.moviesapp.ui.home.HomeViewModel
import com.example.moviesapp.ui.theme.HeroesAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = buildRetrofit()
        val moviesApi = createMoviesApi(retrofit)
        val remoteMovies = RemoteMoviesImpl(moviesApi)
        val moviesRepository = MoviesRepositoryImpl(remoteMovies)
        val viewModel = HomeViewModel(moviesRepository)

        setContent {
            HeroesAppTheme {
                Home(viewModel = viewModel)
            }
        }
    }
}
