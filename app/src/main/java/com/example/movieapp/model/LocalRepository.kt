package com.example.movieapp.model

import com.example.movieapp.room.ViewedFilmsInfo

interface LocalRepository {
    fun getAllSavedMovies(): List<ViewedFilmsInfo>
    fun saveEntity(info: ViewedFilmsInfo)
}
