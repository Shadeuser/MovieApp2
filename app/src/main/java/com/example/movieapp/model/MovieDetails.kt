package com.example.movieapp.model

class MovieDetails() {
    val adult: Boolean = true

    val id: Long = 0

    val budget: Int = 0

    val title: String = ""

    val tagline: String = ""

    val vote_average: Double = 0.00

    val poster_path: String = ""
        get() = "https://image.tmdb.org/t/p/w500$field"

    val overview: String = ""

    val genres: MutableList<Genres>? = null

    val release_date: String = ""

    val vote_count: Int = 0

    val runtime: Int = 0

}


