package com.example.movieapp.model

import android.os.Parcelable

import com.example.movieapp.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    val film: String = "",
    val poster: Int = 0,
    val genre: String = "",
    val year: Int = 0
        ):Parcelable

fun getWorldFilms(): List<Film> {
    return listOf(
        Film("Хижина в лесу", R.drawable.higina, "Ужасы, комедия, фэнтези", 2019),
        Film("Начало", R.drawable.inception, "фантастика, боевик, триллер, драма, детектив", 2010),
        Film("Одержимость", R.drawable.whiplash, "драма, музыка", 2013   ),
        Film("Один дома", R.drawable.home_alone, "комедия, семейный", 1990),
        Film("Терминатор 2: Судный день", R.drawable.terminator2, "фантастика, боевик, триллер", 2019),
        Film("Крестный отец ",R.drawable.the_godfather, "драма, криминал", 2019),
        Film("Назад в будущее 2", R.drawable.back_to_the_future_part_2, "фантастика, приключения, боевик, комедия, семейный", 2019),
        Film("Молчание ягнят", R.drawable.the_silence_of_the_lambs, "триллер, детектив, криминал, драма, ужасы", 2019),
        Film("Чужой", R.drawable.alien, "ужасы, фантастика, триллер", 2019),
    )


}