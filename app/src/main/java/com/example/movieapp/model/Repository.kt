package com.example.movieapp.model

interface Repository {
    fun getFilmFromLocalStorage(): Film
    fun getFilmFromRemoteStorage(): Film
}