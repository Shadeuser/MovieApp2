package com.example.movieapp.viewmodel

import com.example.movieapp.model.Film
import com.example.movieapp.model.PopularMovies
import com.example.movieapp.room.ViewedFilmsInfo

sealed class AppState {
    data class Success(val filmData: PopularMovies): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
