package com.katfox.movieappsimple.feature_movie.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import coil.load
import com.katfox.movieappsimple.R
import com.katfox.movieappsimple.core.util.loadWithCoil
import com.katfox.movieappsimple.core.util.toImage
import com.katfox.movieappsimple.databinding.FragmentDetailBinding
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val args = requireArguments().getSerializable("movie") as MovieInfo

        binding.apply {
            loadWithCoil(iv, args.posterPath.toImage())
            tvTitle.text = args.title
            tvDesc.text = args.overview
            tvType.text = "Movie type: ${args.type}"
            tvDate.text = "Release day: ${args.releaseDay}"
        }
    }

}