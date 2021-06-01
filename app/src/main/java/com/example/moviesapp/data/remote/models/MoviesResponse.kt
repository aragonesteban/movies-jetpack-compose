package com.example.moviesapp.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val results: List<MovieResponse>
)