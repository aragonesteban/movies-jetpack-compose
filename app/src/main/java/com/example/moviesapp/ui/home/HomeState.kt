package com.example.moviesapp.ui.home

import com.example.moviesapp.domain.models.Movie

data class HomeState(
    val onPlayingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: Boolean = false
)
