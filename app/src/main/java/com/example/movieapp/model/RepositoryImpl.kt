package com.example.movieapp.model

class RepositoryImpl: Repository{

    override fun getFilmFromLocalStorage(): Film {
        return Film()
    }

    override fun getFilmFromRemoteStorage(): Film {
        return Film()
    }

    override fun getWorldFilmsFromLocalStorage(): List<Film> {
        return getWorldFilms()
    }
}