package com.example.movieapp.viewmodel

import com.example.movieapp.model.Film
import java.lang.Exception

sealed class AppState {
    data class Success(val filmData: Film) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
