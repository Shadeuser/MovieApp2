package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.*

import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.ViewModelBase
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.joinAll


class MainFragment : Fragment() {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModelBase




    private val adapter = MainAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: FilmShortDetails) {
            super.onItemViewClick(film)
            val manager = activity?.supportFragmentManager
            if (manager != null) {
                val bundle = Bundle()
                bundle.putParcelable(FilmDetailsFragment.BUNDLE_EXTRA, film)
                manager.beginTransaction()
                    .replace(R.id.container, FilmDetailsFragment.newInsance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.mainRecyclerView.adapter = adapter
//        viewModel = ViewModelProvider(this).get(ViewModelBase::class.java)
//        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { prepareData(it) })
//        viewModel.getWorldFilmsFromLocalSource()

        binding.mainRecyclerView.adapter = adapter


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val filmServer = FilmServer(binding)
        filmServer.getPopularMovieList(filmServer.buildPopMovieListUrl("ru", 1), adapter)

//        viewModel = ViewModelProvider(this).get(ViewModelBase::class.java)
//        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { prepareData(it) })
//        viewModel.getWorldFilmsFromLocalSource()

    }


    private fun prepareData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
//                adapter.filmData = appState.filmData
            }
            is AppState.Loading -> {
//                binding.txtFilmName.text = "Loading"
            }
            is AppState.Error -> {
//                binding.txtFilmName.text = "Error"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: FilmShortDetails) {

        }
    }




}