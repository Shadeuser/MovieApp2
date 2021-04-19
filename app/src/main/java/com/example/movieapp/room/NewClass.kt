package com.example.movieapp.room

class NewClass(private val localDataSource: ViewedFilmsDao) {

    private fun convertEntityToInfo(entityList: List<ViewedFilmsEntity>): List<ViewedFilmsInfo> {
        return entityList.map {
            ViewedFilmsInfo(it.name, it.movieId, it.posterPath)
        }
    }

    private fun convertInfoToEntity(info: ViewedFilmsInfo): ViewedFilmsEntity {
        return ViewedFilmsEntity(0, info.name, info.movieId, info.posterPath)
    }

    fun saveInfoToDb(info: ViewedFilmsInfo) {
        localDataSource.insert(convertInfoToEntity(info))
    }

    fun getAllViewedMovies(): List<ViewedFilmsInfo> {
        return convertEntityToInfo(localDataSource.getAll())
    }

//    fun deleteMovie(name: String) {
//        localDataSource.deleteByEntity(name)
//    }

    fun getMovieById(in_movie_id: Long): List<ViewedFilmsEntity> {
        return localDataSource.getMovieById(in_movie_id)
    }

    fun deleteByEntity(entityList: List<ViewedFilmsEntity>) {
        localDataSource.deleteByEntity(entityList)
    }


    fun deleteAll() {
        localDataSource.deleteAll()
    }
}