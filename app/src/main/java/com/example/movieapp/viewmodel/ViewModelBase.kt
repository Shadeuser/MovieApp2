package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.*
import retrofit2.Call
import retrofit2.Response

//    private val repositoryImpl: Repository = RepositoryImpl()
class ViewModelBase (
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val movieRepositoryImpl: MovieRepositoryImpl = MovieRepositoryImpl(RemoteDataSource())
): ViewModel() {


    fun getLiveData() = liveDataToObserve
    fun getPopularMovieFromRemoteSource(language: String, page: Int) {
        liveDataToObserve.value = AppState.Loading
        movieRepositoryImpl.getPopularFilmsRetrofit(language, page, callBack)
    }

    private val callBack = object : retrofit2.Callback<PopularMovies> {

        override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
            val serverResponse: PopularMovies? = response.body()
            if (response.isSuccessful && serverResponse != null) {

             liveDataToObserve.postValue(AppState.Success(serverResponse))
//                AppState.Success(serverResponse)
            } else {
                liveDataToObserve.postValue(AppState.Error(Throwable("ОШИБКА СЕРВЕРА")))
//                AppState.Error(Throwable("ОШИБКА СЕРВЕРА!"))
            }
        }

        override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(Throwable(t.message ?: "ОШИБКА ЗАПРОСА НА СЕВЕР")))
        }
    }






}


