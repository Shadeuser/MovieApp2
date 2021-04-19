package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.app.App
import com.example.movieapp.model.LocalRepository
import com.example.movieapp.model.LocalRepositoryImpl

class ViewedMovieViewModel(
    val liveDataToObserve: MutableLiveData<AppState2> = MutableLiveData(),
    private val viewedRepository: LocalRepository =
        LocalRepositoryImpl(App.getViewedFilmsDao())
): ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getAllSavedMovies() {
        liveDataToObserve.value = AppState2.Loading
        liveDataToObserve.value = AppState2.Success(viewedRepository.getAllSavedMovies())
    }
}