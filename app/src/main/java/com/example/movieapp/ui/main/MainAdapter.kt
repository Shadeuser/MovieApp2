package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Film

class MainAdapter(
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener?
    ): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var filmData: List<Film> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.main_recycler_item, parent, false) as View)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmData[position])
    }

    override fun getItemCount(): Int {
        return filmData.size
    }


    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(film: Film) {
            itemView.findViewById<TextView>(R.id.txtViewMainRecycclerItem).text = film.film
            itemView.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(film)
            }
        }
    }


}