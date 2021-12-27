package com.katfox.movieappsimple.feature_movie.domain.repository

import com.katfox.movieappsimple.core.util.Resources
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieInfos(): Flow<Resources<List<MovieInfo>>>

}