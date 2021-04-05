package com.example.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilmShortDetails: Parcelable {
    val id: Int = -1

    val original_language: String = ""

    val overview: String = ""

    val title: String = ""

    val poster_path = ""
    get() = "https://image.tmdb.org/t/p/w500$field"
}