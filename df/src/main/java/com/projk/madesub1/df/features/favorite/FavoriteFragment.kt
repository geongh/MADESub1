package com.projk.madesub1.df.features.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.projk.madesub1.core.ui.MoviesAdapter
import com.projk.madesub1.detail.DetailMoviesActivity
import com.projk.madesub1.df.databinding.FragmentFavoriteBinding
import com.projk.madesub1.df.features.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loadKoinModules(favoriteModule)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMoviesActivity::class.java)
                intent.putExtra(DetailMoviesActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, { favoriteMovies ->
                moviesAdapter.setData(favoriteMovies)
                if (favoriteMovies.isEmpty()) {
                    Toast.makeText(context, "No Movies Added", Toast.LENGTH_SHORT).show()
                }
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}