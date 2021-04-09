package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.*

class MainAdapter(
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener?
    ): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    var filmData: PopularMovies? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.main_recycler_item, parent, false) as View)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (filmData != null) {
            val film = filmData?.results!![position]
            holder.bind(film)
        }
    }

    override fun getItemCount(): Int {
        return filmData?.results?.size ?: return 0
    }


    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(film: FilmShortDetails) {
            itemView.findViewById<TextView>(R.id.txtViewMainRecycclerItem).text = film.title
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(film)
            }
        }
    }
}