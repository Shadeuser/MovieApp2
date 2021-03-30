package com.example.movieapp.ui.main

import android.os.Bundle
import android.telecom.Call

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFilmDetailsBinding
import com.example.movieapp.model.Film


class FilmDetailsFragment : Fragment() {
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() =  _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_film_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA) //??
        if (film != null) {
            binding.txtFilmName.text = film.film
            binding.txtGenre.text = film.genre
            binding.txtYear.text = film.year.toString()
            binding.imgFilmPoster.setImageResource(film.poster)
        }

    }


    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInsance(bundle: Bundle): FilmDetailsFragment {
            val fragment = FilmDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }



}