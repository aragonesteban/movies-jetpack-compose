package com.example.moviesapp.data.remote.models

import com.example.moviesapp.domain.models.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun List<MovieResponse>.transformToMovie(): List<Movie> {
    return map { movieResponse ->
        Movie(
            isToAdult = movieResponse.adult ?: false,
            imageBackdrop = movieResponse.backdrop_path ?: "",
            id = movieResponse.id ?: 0,
            language = movieResponse.original_language ?: "",
            originalTitle = movieResponse.original_title ?: "",
            overview = movieResponse.overview ?: "",
            popularity = movieResponse.popularity ?: 1.0,
            imagePoster = "https://image.tmdb.org/t/p/w500/${movieResponse.poster_path}",
            releaseDate = movieResponse.release_date ?: "",
            title = movieResponse.title ?: "",
            hasVideo = movieResponse.video ?: false,
            voteAverage = movieResponse.vote_average ?: 1.0,
            voteCount = movieResponse.vote_count ?: 0,
        )
    }
}