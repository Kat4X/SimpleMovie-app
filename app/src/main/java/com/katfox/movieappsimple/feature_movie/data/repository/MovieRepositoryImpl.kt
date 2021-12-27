package com.katfox.movieappsimple.feature_movie.data.repository

import com.katfox.movieappsimple.core.util.Resources
import com.katfox.movieappsimple.feature_movie.data.local.MovieInfoDao
import com.katfox.movieappsimple.feature_movie.data.remote.MovieApi
import com.katfox.movieappsimple.feature_movie.domain.model.MovieInfo
import com.katfox.movieappsimple.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieInfoDao
) : MovieRepository {

    override fun getMovieInfos(): Flow<Resources<List<MovieInfo>>> = flow {
        emit(Resources.Loading())

        val movieInfos = dao.getMovieInfos().map { it.toMovieInfo() }
        emit(Resources.Loading(data = movieInfos))

        try {
            val remoteMovieInfos = api.loadMovie()
            dao.deleteMovieInfos(remoteMovieInfos.toResultsList().map { it.title })
            dao.insertMovieInfos(remoteMovieInfos.toResultsList().map { it.toMovieInfoEntity() })
        } catch (e: HttpException) {
            emit(Resources.Error(message = "Oops, something went wrong!", data = movieInfos))
        } catch (e: IOException) {
            emit(Resources.Error(message = "Check your internet connection", data = movieInfos))
        }

        val newMovieInfos = dao.getMovieInfos().map { it.toMovieInfo() }
        emit(Resources.Success(newMovieInfos))
    }
}