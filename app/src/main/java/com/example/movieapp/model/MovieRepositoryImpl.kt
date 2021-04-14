package com.example.movieapp.model

import retrofit2.Callback

class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource)  : MovieRepository {

    override fun getPopularFilmsRetrofit(
        language: String,
        page: Int,
        callback: Callback<PopularMovies>
    ) {
        remoteDataSource.getPopularFilmInfoFromRemoteServer(language, page, callback)
    }

    override fun getFilmDetailsRetrofit(
        id: Int,
        language: String,
        callback: Callback<MovieDetails>
    ) {
        remoteDataSource.getCurrentMovieDetailsFromRemoteServer(id, language, callback)
    }
}