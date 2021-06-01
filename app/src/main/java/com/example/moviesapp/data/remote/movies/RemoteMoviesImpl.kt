package com.example.moviesapp.data.remote.movies

import com.example.moviesapp.data.remote.models.MoviesResponse
import com.example.moviesapp.data.remote.models.transformToMovie
import com.example.moviesapp.data.remote.retrofit.MoviesApi
import com.example.moviesapp.data.remote.retrofit.executeRetrofitRequest
import com.example.moviesapp.domain.NetworkResult
import com.example.moviesapp.domain.models.Movie

class RemoteMoviesImpl(private val moviesApi: MoviesApi) : RemoteMovies {

    override suspend fun getOnPlayingMovies(): NetworkResult<List<Movie>> {
        val result = executeRetrofitRequest { moviesApi.getOnPlayingMovies() }
        return handleResponseMovies(result)
    }

    override suspend fun getPopularMovies(): NetworkResult<List<Movie>> {
        val result = executeRetrofitRequest { moviesApi.getPopularMovies() }
        return handleResponseMovies(result)
    }

    private fun handleResponseMovies(result: NetworkResult<MoviesResponse>): NetworkResult<List<Movie>> {
        if (result is NetworkResult.Success) {
            return NetworkResult.Success(result.data.results.transformToMovie())
        }
        return NetworkResult.Error(result.toString())
    }

}

