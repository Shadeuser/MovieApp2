package com.example.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmShortDetails(
    val id: Long = -1,
    val original_language: String = "",
    val overview: String = "",
    val title: String = "",
    private val poster_path: String = ""

): Parcelable {

    fun getPosterPath (): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }


}