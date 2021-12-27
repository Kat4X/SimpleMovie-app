package com.katfox.movieappsimple.feature_movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.katfox.movieappsimple.feature_movie.data.local.entity.MovieInfoEntity

@Database(
    entities = [MovieInfoEntity::class],
    version = 1
)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract val movieInfoDao: MovieInfoDao
}