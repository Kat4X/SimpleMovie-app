package com.katfox.movieappsimple.feature_movie.data.remote

import com.katfox.movieappsimple.feature_movie.data.remote.dto.MovieDto
import retrofit2.http.GET

interface MovieApi {

    @GET("3/trending/all/day")
    suspend fun loadMovie(): MovieDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }
}