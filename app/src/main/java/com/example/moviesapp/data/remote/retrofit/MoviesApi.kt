package com.example.moviesapp.data.remote.retrofit

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.remote.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val NOW_PLAYING = "now_playing"
private const val POPULAR = "popular"
private const val API_KEY = "api_key"
private const val LANGUAGE = "language"
private const val LANGUAGE_EN = "en-US"
private const val PAGE = "page"

interface MoviesApi {

    @GET(NOW_PLAYING)
    suspend fun getOnPlayingMovies(
        @Query(API_KEY) apikey: String = BuildConfig.API_KEY_MOVIE,
        @Query(LANGUAGE) language: String = LANGUAGE_EN,
        @Query(PAGE) page: Int = 1,
    ): Response<MoviesResponse>

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query(API_KEY) apikey: String = BuildConfig.API_KEY_MOVIE,
        @Query(LANGUAGE) language: String = LANGUAGE_EN,
        @Query(PAGE) page: Int = 1,
    ): Response<MoviesResponse>

}