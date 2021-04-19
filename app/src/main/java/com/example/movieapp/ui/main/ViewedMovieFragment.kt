package com.example.movieapp.ui.main

import android.graphics.Color
import android.icu.text.IDNA
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.app.App
import com.example.movieapp.databinding.FragmentViewedMovieBinding
import com.example.movieapp.model.FilmShortDetails
import com.example.movieapp.room.NewClass
import com.example.movieapp.room.ViewedFilmsInfo
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.AppState2
import com.example.movieapp.viewmodel.ViewModelBase
import com.example.movieapp.viewmodel.ViewedMovieViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.StringBuilder


class ViewedMovieFragment : Fragment() {
    private var _binding: FragmentViewedMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewedMovieViewModel by lazy {
        ViewModelProvider(this).get(ViewedMovieViewModel::class.java)
    }

    private val  adapter = ViewedMovieAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(info: ViewedFilmsInfo) {
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                var film = FilmShortDetails(info.movieId)

                bundle.putParcelable(FilmDetailsFragment.BUNDLE_EXTRA, film)
                manager.beginTransaction()
                    .replace(R.id.container, FilmDetailsFragment.newInsance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }

        }
    })



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewedMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewedRecyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, { prepareData(it) })
        viewModel.getAllSavedMovies()
        binding.btnBackToMain.setOnClickListener {
            view.quickSnackBar("Вернуться на экран поиска?", "Да",
                Color.BLUE, View.OnClickListener {
                    val manager = activity?.supportFragmentManager
                    manager?.beginTransaction()?.replace(R.id.container, MainFragment.newInstance())
                        ?.commitNow()
                })
        }
    }


    companion object {
        const val BUNDLE_MOVIE = "viewed movie details"
        @JvmStatic
        fun newInstance() = ViewedMovieFragment()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(info: ViewedFilmsInfo)
    }

    private fun prepareData(appState: AppState2) {
        when (appState) {
            is AppState2.Success -> {
                adapter.viewedFilminfoList = appState.filmData
            }
            is AppState2.Loading -> {
//                binding.txtFilmName.text = "Loading"
            }
            is AppState2.Error -> {
//                binding.txtFilmName.text = "Error"
            }
        }
    }

    fun View.quickSnackBar(
        text: String,
        actionText: String,
        backGroundColor: Int,
        listener: View.OnClickListener
    ) {
        val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        snackbar.setAction(actionText, listener)
        snackbar.view.setBackgroundColor(backGroundColor)
        snackbar.show()
    }


}