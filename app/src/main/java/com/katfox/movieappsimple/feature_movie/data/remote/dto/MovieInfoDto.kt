package com.katfox.movieappsimple.feature_movie.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo

@Keep
data class MovieDto(
    @SerializedName("page")
    val page: Int? = null, // 1
    @SerializedName("results")
    val results: List<MovieInfoDto>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null, // 1000
    @SerializedName("total_results")
    val totalResults: Int? = null // 20000
) {
    @Keep
    data class MovieInfoDto(
        @SerializedName("overview")
        val overview: String? = null, // Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.
        @SerializedName("release_date")
        val releaseDate: String? = null, // 2021-12-15
        @SerializedName("adult")
        val adult: Boolean? = null, // false
        @SerializedName("backdrop_path")
        val backdropPath: String? = null, // /1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg
        @SerializedName("genre_ids")
        val genreIds: List<Int?>? = null,
        @SerializedName("vote_count")
        val voteCount: Int? = null, // 2536
        @SerializedName("original_language")
        val originalLanguage: String? = null, // en
        @SerializedName("id")
        val id: Int? = null, // 634649
        @SerializedName("poster_path")
        val posterPath: String? = null, // /1g0dhYtq4irTY1GPXvft6k4YLjm.jpg
        @SerializedName("title")
        val title: String = "", // Spider-Man: No Way Home
        @SerializedName("video")
        val video: Boolean? = null, // false
        @SerializedName("vote_average")
        val voteAverage: Double? = null, // 8.5
        @SerializedName("original_title")
        val originalTitle: String? = null, // Spider-Man: No Way Home
        @SerializedName("popularity")
        val popularity: Double? = null, // 14959.911
        @SerializedName("media_type")
        val mediaType: String? = null, // movie
        @SerializedName("name")
        val name: String? = null, // Hawkeye
        @SerializedName("first_air_date")
        val firstAirDate: String? = null, // 2021-11-24
        @SerializedName("original_name")
        val originalName: String? = null, // Hawkeye
        @SerializedName("origin_country")
        val originCountry: List<String?>? = null
    ) {
        fun toMovie(): MovieInfo {
            return MovieInfo(
                overview = overview,
                id = id,
                posterPath = posterPath,
                title = title,
                type = mediaType ?: "",
                releaseDay = releaseDate ?: ""
            )
        }
    }

    fun toResultsList(): List<MovieInfo> {
        return results?.map { it.toMovie() } ?: emptyList()
    }
}