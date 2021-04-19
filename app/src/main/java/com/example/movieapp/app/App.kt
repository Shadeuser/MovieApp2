package com.example.movieapp.app

import android.app.Application
import androidx.room.Room
import com.example.movieapp.room.ViewedFilmsDao
import com.example.movieapp.room.ViewedFilmsDatabase
import java.lang.IllegalStateException

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstanse = this
    }

    companion object {
        private var appInstanse: App? = null
        private var db: ViewedFilmsDatabase? = null
        private const val DB_NAME = "Films_viewed.db"

        fun getViewedFilmsDao(): ViewedFilmsDao {
            if (db == null) {
                synchronized(ViewedFilmsDatabase::class.java) {
                    if (db == null) {
                        if (appInstanse == null) throw
                                IllegalStateException("Application is null while creating Database")
                        db = Room.databaseBuilder(
                            appInstanse!!.applicationContext,
                            ViewedFilmsDatabase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.viewedFilmDao()
        }


//        fun getHistoryDao(): HistoryDao {
//            if (db == null) {
//                synchronized(HistoryDataBase::class.java) {
//                    if (db == null) {
//                        if (appInstance == null) throw
//                        IllegalStateException("Application is null while creating DataBase")
//                        db = Room.databaseBuilder(
//                            appInstance!!.applicationContext,
//                            HistoryDataBase::class.java,
//                            DB_NAME)
//                            .allowMainThreadQueries()
//                            .build()
//                    }
//                }
//            }
//            return db!!.historyDao()
//        }

    }
}