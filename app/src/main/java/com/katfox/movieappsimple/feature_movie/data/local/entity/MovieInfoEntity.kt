package com.katfox.movieappsimple.feature_movie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo

@Entity
data class MovieInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val title: String = "",
    val type: String = "",
    val releaseDay: String = ""
) {
    fun toMovieInfo(): MovieInfo {
        return MovieInfo(
            overview = overview,
            posterPath = posterPath,
            title = title,
            id = id,
            type = type,
            releaseDay = releaseDay,
        )
    }
}
