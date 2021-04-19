package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.FilmShortDetails
import com.example.movieapp.model.PopularMovies
import com.example.movieapp.room.ViewedFilmsInfo

class ViewedMovieAdapter (
    private var onItemViewClickListener: ViewedMovieFragment.OnItemViewClickListener?
        ): RecyclerView.Adapter<ViewedMovieAdapter.ViewedMovieHolder>() {
        var viewedFilminfoList: List<ViewedFilmsInfo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedMovieHolder {
        return ViewedMovieHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.viewed_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: ViewedMovieHolder, position: Int) {
        if (viewedFilminfoList != null) {
            val viewedMovie = viewedFilminfoList!![position]
            holder.bind(viewedMovie)
        }
    }

    override fun getItemCount(): Int {
        return viewedFilminfoList?.size ?: 0
    }

    inner class ViewedMovieHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(viewedMovie: ViewedFilmsInfo) {
            itemView.findViewById<TextView>(R.id.txtViewedItem).text = viewedMovie.name

            itemView.setOnClickListener{
                onItemViewClickListener?.onItemViewClick(viewedMovie)
            }
        }
    }


}




