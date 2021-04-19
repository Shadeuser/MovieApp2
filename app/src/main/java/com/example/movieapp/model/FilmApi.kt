package com.example.movieapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {
    @GET("3/movie/popular")
    fun getPopularFilmsInfo(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularMovies>

    @GET("3/movie/popular/")
    fun getCurrentMovieDetailsInfo(
        @Query("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<MovieDetails>

}