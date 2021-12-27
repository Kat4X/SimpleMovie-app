package com.katfox.movieappsimple.feature_movie.domain.use_case

import com.katfox.movieappsimple.core.util.Resources
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo
import com.katfox.movieappsimple.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieInfos(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resources<List<MovieInfo>>> {
        return repository.getMovieInfos()
    }
}