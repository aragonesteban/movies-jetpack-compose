package com.example.moviesapp.domain.models

data class Movie(
    val isToAdult: Boolean,
    val imageBackdrop: String,
    val id: Int,
    val language: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val imagePoster: String,
    val releaseDate: String,
    val title: String,
    val hasVideo: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
