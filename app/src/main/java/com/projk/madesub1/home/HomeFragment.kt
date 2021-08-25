package com.projk.madesub1.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.projk.madesub1.core.data.Resource
import com.projk.madesub1.core.ui.MoviesAdapter
import com.projk.madesub1.databinding.FragmentHomeBinding
import com.projk.madesub1.detail.DetailMoviesActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
                val moviesAdapter = movies.data?.let { MoviesAdapter(it) }
                moviesAdapter?.onItemClick = { selectedData ->
                    val intent = Intent(activity, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_DATA, selectedData)
                    startActivity(intent)
                }

                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            moviesAdapter?.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }

                    with(binding.rvMovies) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = moviesAdapter
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}