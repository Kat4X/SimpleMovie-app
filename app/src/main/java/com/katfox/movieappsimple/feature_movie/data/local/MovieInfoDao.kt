package com.katfox.movieappsimple.feature_movie.data.local

import androidx.room.*
import com.katfox.movieappsimple.feature_movie.data.local.entity.MovieInfoEntity

@Dao
interface MovieInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieInfos(infos: List<MovieInfoEntity>)

    @Query("delete from movieinfoentity where title in (:movie)")
    suspend fun deleteMovieInfos(movie: List<String>)

    @Query("select * from movieinfoentity")
    suspend fun getMovieInfos(): List<MovieInfoEntity>
}