package com.katfox.movieappsimple.feature_movie.domain.model

import com.katfox.movieappsimple.feature_movie.data.local.entity.MovieInfoEntity
import java.io.Serializable

data class MovieInfo(
    val overview: String? = null,
    val id: Int? = null,
    val posterPath: String? = null,
    val title: String = "",
    val type: String = "",
    val releaseDay: String = ""
) : Serializable {
    fun toMovieInfoEntity(): MovieInfoEntity {
        return MovieInfoEntity(id, overview, posterPath, title, type, releaseDay)
    }
}