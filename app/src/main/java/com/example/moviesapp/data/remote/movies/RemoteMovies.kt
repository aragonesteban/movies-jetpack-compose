package com.example.moviesapp.data.remote.movies

import com.example.moviesapp.domain.NetworkResult
import com.example.moviesapp.domain.models.Movie

interface RemoteMovies {
    suspend fun getOnPlayingMovies(): NetworkResult<List<Movie>>
    suspend fun getPopularMovies(): NetworkResult<List<Movie>>
}