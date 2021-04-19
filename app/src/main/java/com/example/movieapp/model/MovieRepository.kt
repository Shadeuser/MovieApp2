package com.example.movieapp.model


interface MovieRepository {
    fun getPopularFilmsRetrofit(
        language: String,
        page: Int,
        callback: retrofit2.Callback<PopularMovies>
    )

    fun getFilmDetailsRetrofit(
        id: Int,
        language: String,
        callback: retrofit2.Callback<MovieDetails>
    )
}