package com.example.movieapp.viewmodel

import com.example.movieapp.model.PopularMovies

sealed class AppState {
    data class Success(val filmData: PopularMovies): AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
