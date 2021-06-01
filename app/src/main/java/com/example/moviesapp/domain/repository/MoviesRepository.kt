package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.NetworkResult
import com.example.moviesapp.domain.models.Movie

interface MoviesRepository {
    suspend fun getMoviesOnPlaying(): NetworkResult<List<Movie>>
    suspend fun getPopularMovies(): NetworkResult<List<Movie>>
}