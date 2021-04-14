package com.example.movieapp.model

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/"

class RemoteDataSource {
    private val filmApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        ).build().create(FilmApi::class.java)

    fun getPopularFilmInfoFromRemoteServer(language: String, page: Int, callBack: Callback<PopularMovies>) {
        filmApi.getPopularFilmsInfo(API_KEY, language, page).enqueue(callBack)
    }

    fun getCurrentMovieDetailsFromRemoteServer(id: Int, language: String, callBack: Callback<MovieDetails>) {
        filmApi.getCurrentMovieDetailsInfo(id, API_KEY, language).enqueue(callBack)
    }
}