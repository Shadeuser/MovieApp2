package com.example.movieapp.model

interface Repository {
    fun getFilmFromLocalStorage(): Film
    fun getFilmFromRemoteStorage(): PopularMovies
    fun getWorldFilmsFromLocalStorage(): List<Film>

}