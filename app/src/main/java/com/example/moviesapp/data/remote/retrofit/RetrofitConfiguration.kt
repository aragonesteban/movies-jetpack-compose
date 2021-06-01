package com.example.moviesapp.data.remote.retrofit

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.domain.NetworkResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

fun logging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}

fun provideHttpclient(): OkHttpClient = OkHttpClient()
    .newBuilder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .addInterceptor(logging())
    .build()

fun buildRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(provideHttpclient())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createMoviesApi(retrofit: Retrofit): MoviesApi {
    return retrofit.create(MoviesApi::class.java)
}

internal inline fun <T : Any> executeRetrofitRequest(block: () -> Response<T>): NetworkResult<T> {
    return try {
        val request = block.invoke()
        return if (request.isSuccessful && request.body() != null) {
            val body = request.body()
            if (body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error("Empty body found in this request")
            }
        } else {
            val errorBody = request.errorBody()
            val errorText = errorBody?.string() ?: "Error body null"
            NetworkResult.Error(errorText)
        }
    } catch (exception: UnknownHostException) {
        NetworkResult.Error(exception.toString())
    }
}
