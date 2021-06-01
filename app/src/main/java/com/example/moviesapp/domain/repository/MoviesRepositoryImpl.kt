package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.remote.movies.RemoteMovies
import com.example.moviesapp.domain.NetworkResult
import com.example.moviesapp.domain.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(private val remoteMovies: RemoteMovies) : MoviesRepository {

    override suspend fun getMoviesOnPlaying(): NetworkResult<List<Movie>> {
        return withContext(Dispatchers.IO) {
            remoteMovies.getOnPlayingMovies()
        }
    }

    override suspend fun getPopularMovies(): NetworkResult<List<Movie>> {
        return withContext(Dispatchers.IO) {
            remoteMovies.getPopularMovies()
        }
    }

}