package com.example.movieapp.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.movieapp.MainBroadCastReceiver
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFilmDetailsBinding
import com.example.movieapp.model.FilmServer
import com.example.movieapp.model.FilmShortDetails
import com.google.android.material.snackbar.Snackbar

class FilmDetailsFragment : Fragment(){
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() =  _binding!!
    lateinit var btnContinue: Button

    private val film: FilmShortDetails by lazy{
        arguments?.getParcelable(BUNDLE_EXTRA) ?: FilmShortDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmServer = FilmServer(binding)
        filmServer.getCurrentMovieDetails(filmServer.buildMovieDetailsUrl(film.id), view)
        btnContinue = view.findViewById(R.id.btnContinue)
        btnContinue.setOnClickListener {
            val receiver = MainBroadCastReceiver()
            val intent: Intent = Intent(NEW_CAT_DETECTED)
            intent.putExtra("MSG!!!!!!!!!!!", "Здравствуйте, я кот!")

//            requireActivity().registerReceiver(receiver, NEW_CAT_DETECTED)
//            view.quickSnackBar("Вернуться на экран поиска?", "Да",
//            Color.BLUE, View.OnClickListener {
//                val manager = activity?.supportFragmentManager
//                    manager?.beginTransaction()?.replace(R.id.container, MainFragment.newInstance())
//                        ?.commitNow()
//            })
        }
    }

    companion object {
        const val NEW_CAT_DETECTED ="Кошка обнаружена!"
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






