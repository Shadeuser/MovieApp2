package com.example.movieapp.model

interface Repository {
    fun getFilmFromLocalStorage(): Film
    fun getFilmFromRemoteStorage(): Film
    fun getWorldFilmsFromLocalStorage(): List<Film>

}