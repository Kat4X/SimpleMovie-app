package com.katfox.movieappsimple.feature_movie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katfox.movieappsimple.core.util.Resources
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo
import com.katfox.movieappsimple.feature_movie.domain.use_case.GetMovieInfos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val getMovieInfos: GetMovieInfos
) : ViewModel() {

    private val _movieList = MutableStateFlow<Resources<List<MovieInfo>>>(Resources.Loading())
    val movieList get() = _movieList.asStateFlow()

    fun getMovie() {
        viewModelScope.launch {
            getMovieInfos().onEach { result ->
                when (result) {
                    is Resources.Error -> {
                        _movieList.emit(result)
                    }
                    is Resources.Loading -> {
                        _movieList.emit(result)
                    }
                    is Resources.Success -> {
                        _movieList.emit(result)
                        Log.d("ASL", "$result")
                    }
                }

            }.launchIn(this)
        }
    }

}