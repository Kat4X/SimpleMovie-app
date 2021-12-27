package com.katfox.movieappsimple.feature_movie.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katfox.movieappsimple.R
import com.katfox.movieappsimple.core.util.Resources
import com.katfox.movieappsimple.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by activityViewModels<MovieInfoViewModel>()

    private var movieAdapter: MovieAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        viewModel.getMovie()
        initAdapter()

        lifecycleScope.launchWhenStarted {
            viewModel.movieList.collect { result ->
                when (result) {
                    is Resources.Error -> {
                        Toast.makeText(requireContext(), "${result.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is Resources.Loading -> {}
                    is Resources.Success -> {
                        movieAdapter?.differ?.submitList(result.data)
                    }
                }
                Log.wtf("CollectionResult: ", "State: $result, Result: ${result.data}")
            }
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = movieAdapter
        }

        return binding.root
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter()
        movieAdapter?.setOnItemClickListener { movie ->
            movie?.let {
                val bundle = bundleOf("movie" to it)
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            }
        }
    }
}