package com.example.movieapp.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewbinding.ViewBinding
import com.example.movieapp.databinding.FragmentFilmDetailsBinding
import com.example.movieapp.ui.main.MainAdapter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection
class FilmServer<T : ViewBinding>(
    val binding: T

) {
    private companion object {
        val CURRENT_MOVIE_URL_STARTS = "https://api.themoviedb.org/3/movie/"
        val POPULAR_MOVIE_LIST_URL_STARTS = "https://api.themoviedb.org/3/movie/popular?api_key="
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentMovieDetails(
        urlInputText: String,
        view: View
    ) {
        val uri = URL(urlInputText)
        val handler = Handler(Looper.getMainLooper())
        Thread {
            try {
                val movieDetails = Gson().fromJson(getJsonText(uri), MovieDetails::class.java)
                handler.post {
                    bindFilmDetails(
                        movieDetails,
                        view,
                        binding as FragmentFilmDetailsBinding
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPopularMovieList(
        urlInputText: String,
        adapter: MainAdapter
    ) {
        val uri = URL(urlInputText)
        val handler = Handler(Looper.getMainLooper())
        Thread {
            try {
                val popularMovies = Gson().fromJson(getJsonText(uri), PopularMovies::class.java)
                handler.post {

                    bindPopularFilmList(adapter, popularMovies)
                }
            } catch (e: Exception) {
                e.printStackTrace() //log
            }
        }.start()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getJsonText(uri: URL): String {
        var urlConnection: HttpsURLConnection? = null
        try {
            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 5000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            return getStringLines(reader)
        } finally {
            urlConnection?.disconnect()
        }
    }

    private fun bindFilmDetails(
        movieDetails: MovieDetails,
        view: View,
        inBinding: FragmentFilmDetailsBinding
    ) {

        inBinding.txtFilmName.text = movieDetails.title
        inBinding.txtFilmContinue.text = "Продолжительность: ${movieDetails.runtime} мин."
        Picasso.with(view.context)
            .load(movieDetails.poster_path)
            .into(
                inBinding.imgFilmPoster
            )
        inBinding.txtFilmOverView.text = movieDetails.overview
        inBinding.txtYear.text = "Релиз: ${movieDetails.release_date}"

        if (movieDetails.genres != null) {
            var genre: String = ""
            inBinding.txtGenre.text = ""
            movieDetails.genres.forEach {
                genre += it.name + ","
            }
            inBinding.txtGenre.text = genre.substring(0, genre.length - 1)
        }
    }

    private fun bindPopularFilmList(adapter: MainAdapter, popularMovies: PopularMovies) {
        adapter.filmData = popularMovies
    }


    private fun getStringLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    fun buildMovieDetailsUrl(
        id: Int,
        language: String = "ru",
        region: String = "",
    ): String {
        var newRegion = ""
        if (region.length > 0) {
            newRegion = "-${region}"
        }
        return "${CURRENT_MOVIE_URL_STARTS}${id}?api_key=${API_KEY}&language=${language}${newRegion}"
    }

    fun buildPopMovieListUrl(
        language: String = "ru",
        page: Int = 1,
        region: String = ""
    ): String {
        var newRegion = ""
        if (region.length > 0) {
            newRegion = "-${region}"
        }
        return "${POPULAR_MOVIE_LIST_URL_STARTS}${API_KEY}&language=${language}${newRegion}&page=${page}"
    }
}