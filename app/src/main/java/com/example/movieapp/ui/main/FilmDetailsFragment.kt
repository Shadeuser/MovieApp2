package com.example.movieapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.telecom.Call

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFilmDetailsBinding
import com.example.movieapp.model.Film
import com.example.movieapp.viewmodel.ViewModelBase
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KProperty


class FilmDetailsFragment : Fragment() {
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() =  _binding!!
    lateinit var btnContinue: Button
    private val film: Film by lazy{
        arguments?.getParcelable(BUNDLE_EXTRA) ?: Film()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_film_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.txtFilmName.text = film.film
            binding.txtGenre.text = film.genre
            binding.txtYear.text = film.year.toString()
            binding.imgFilmPoster.setImageResource(film.poster)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            view.quickSnackBar("Вернуться на экран поиска?", "Да",
            Color.BLUE, View.OnClickListener {
                val manager = activity?.supportFragmentManager
                    manager?.beginTransaction()?.replace(R.id.container, MainFragment.newInstance())
                        ?.commitNow()
            })


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

    fun View.quickSnackBar(
        text: String,
        actionText: String,
        backGroundColor: Int,
    listener: View.OnClickListener) {
         val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        snackbar.setAction(actionText, listener)
        snackbar.view.setBackgroundColor(backGroundColor)
        snackbar.show()

    }




}






