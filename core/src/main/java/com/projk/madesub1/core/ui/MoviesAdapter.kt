package com.projk.madesub1.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projk.madesub1.core.R
import com.projk.madesub1.core.databinding.ItemListMoviesBinding
import com.projk.madesub1.core.domain.model.Movies

class MoviesAdapter(var movies: List<Movies>) : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return

        val diffCallback = MoviesDiffCallback(this.movies, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)
        this.movies = newListData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false))

    override fun onBindViewHolder(holder: MoviesAdapter.ListViewHolder, position: Int) {
        val data = movies[position]
        holder.bind(data)
    }

    override fun getItemCount() = movies.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMoviesBinding.bind(itemView)
        fun bind(data: Movies) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemYear.text = data.releasedate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }
}