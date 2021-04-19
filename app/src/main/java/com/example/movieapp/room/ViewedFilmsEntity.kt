package com.example.movieapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ViewedFilmsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val movieId: Long,
    val posterPath: String
)