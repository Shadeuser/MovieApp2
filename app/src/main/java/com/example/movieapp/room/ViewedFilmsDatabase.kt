package com.example.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(ViewedFilmsEntity::class),
    version = 1,
    exportSchema = false
)

abstract class ViewedFilmsDatabase : RoomDatabase() {
    abstract fun viewedFilmDao(): ViewedFilmsDao
}