package com.projk.madesub1.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.projk.madesub1.R
import com.projk.madesub1.core.domain.model.Movies
import com.projk.madesub1.databinding.ActivityDetailMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {
    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()
    private lateinit var binding: ActivityDetailMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        showDetailMovies(detailMovies)
    }

    private fun showDetailMovies(detailMovies: Movies?) {
        detailMovies?.let {
            supportActionBar?.title = detailMovies.title

            binding.tvItemTitle.text = detailMovies.title
            binding.tvItemYear.text = "Release Date : "+detailMovies.releasedate
            binding.tvDetailDescription.text = detailMovies.storyline
            Glide.with(this@DetailMoviesActivity)
                .load(detailMovies.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovies.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMoviesViewModel.setFavoriteMovies(detailMovies, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}