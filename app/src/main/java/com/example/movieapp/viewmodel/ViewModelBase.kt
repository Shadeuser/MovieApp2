package com.example.movieapp.viewmodel

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Repository
import com.example.movieapp.model.RepositoryImpl
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.net.HttpURLConnection
import java.net.URL
import java.util.logging.Handler
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class ViewModelBase (
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
): ViewModel() {


    fun getLiveData() = liveDataToObserve

//    fun getFilmFromLocalSource() = getDataFromLocalSource()
    fun getWorldFilmsFromLocalSource() = getWorldDataFromLocalSource()

//    fun getFilmFromRemoteSource() = getDataFromRemoteSource()


    private fun getWorldDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWorldFilmsFromLocalStorage()))
        }.start()
    }




}


