package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.databinding.MainFragmentBinding

import com.example.movieapp.model.Film
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.ViewModelBase



class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ViewModelBase
    lateinit var btnContinue: Button





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root   // getRoot()?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            setData(filmData = Film())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelBase::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { prepareData(it) })
        viewModel.getFilmFromLocalSource()

        // TODO: Use the ViewModel
    }



    private fun setData(filmData: Film) {

        binding.txtFilmName.text = filmData.film
        binding.imgFilmPoster.setImageResource(R.drawable.higina)
        binding.txtGenre.text = filmData.genre
        binding.txtYear.text = filmData.year.toString()
    }

    private fun prepareData(appState: AppState) {

        when(appState) {

        }
        when (appState) {
            is AppState.Success -> {
                val filmData = appState.filmData
                binding.txtFilmName.text = "Success"
                setData(filmData)
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


}