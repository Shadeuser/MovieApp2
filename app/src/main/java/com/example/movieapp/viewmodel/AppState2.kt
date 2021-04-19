package com.example.movieapp.viewmodel

import com.example.movieapp.room.ViewedFilmsInfo

sealed class AppState2 {
    data class Success(val filmData: List<ViewedFilmsInfo>): AppState2()
    data class Error(val error: Throwable) : AppState2()
    object Loading : AppState2()
}
