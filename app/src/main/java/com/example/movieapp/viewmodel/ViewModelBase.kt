package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Repository
import com.example.movieapp.model.RepositoryImpl
import java.lang.Thread.sleep

class ViewModelBase (
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
): ViewModel() {


    fun getLiveData() = liveDataToObserve

    fun getFilmFromLocalSource() = getDataFromLocalSource()
    fun getWorldFilmsFromLocalSource() = getDataFromLocalSource()

    fun getFilmFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWorldFilmsFromLocalStorage()))
        }.start()
    }



}