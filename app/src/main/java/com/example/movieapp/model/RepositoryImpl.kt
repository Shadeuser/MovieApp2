package com.example.movieapp.model

class RepositoryImpl: Repository{

    override fun getFilmFromLocalStorage(): Film {
        return Film()
    }

    override fun getFilmFromRemoteStorage(): PopularMovies {
        return PopularMovies()
    }


    override fun getWorldFilmsFromLocalStorage(): List<Film> {
        return getWorldFilms()
    }
}