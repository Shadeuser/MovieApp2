package com.example.movieapp.model

import com.example.movieapp.room.ViewedFilmsDao
import com.example.movieapp.room.ViewedFilmsEntity
import com.example.movieapp.room.ViewedFilmsInfo

class LocalRepositoryImpl(private val localDataSourceView: ViewedFilmsDao): LocalRepository {

    override fun getAllSavedMovies(): List<ViewedFilmsInfo> {
        return convertEntityToInfo(localDataSourceView.getAll())
    }

    override fun saveEntity(info: ViewedFilmsInfo) {
        return localDataSourceView.insert(convertInfoToEntity(info))
    }

    private fun convertInfoToEntity(info: ViewedFilmsInfo): ViewedFilmsEntity {
        return ViewedFilmsEntity(0, info.name, info.movieId, info.posterPath)
    }

    private fun convertEntityToInfo(entityList: List<ViewedFilmsEntity>): List<ViewedFilmsInfo> {
        return entityList.map {
            ViewedFilmsInfo(it.name, it.movieId, it.posterPath)
        }
    }
}