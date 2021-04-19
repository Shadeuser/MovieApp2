package com.example.movieapp.room

import androidx.room.*

@Dao
interface ViewedFilmsDao {

    @Query("DELETE FROM ViewedFilmsEntity")
    fun deleteAll()

    @Query("SELECT * FROM ViewedFilmsEntity WHERE movieId = :in_movie_id")
    fun getMovieById(in_movie_id: Long): List<ViewedFilmsEntity>


    @Query("SELECT * FROM ViewedFilmsEntity")
    fun getAll(): List<ViewedFilmsEntity>

    @Query("SELECT * FROM ViewedFilmsEntity WHERE name LIKE :name")
    fun getDataByWord(name: String): List<ViewedFilmsEntity>

    @Delete()
    fun deleteByEntity(entityList: List<ViewedFilmsEntity>)

    @Update()
    fun update(entity: ViewedFilmsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ViewedFilmsEntity)



}