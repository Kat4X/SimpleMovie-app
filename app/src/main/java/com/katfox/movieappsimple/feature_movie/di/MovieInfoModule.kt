package com.katfox.movieappsimple.feature_movie.di

import android.app.Application
import androidx.room.Room
import com.katfox.movieappsimple.feature_movie.data.local.MovieAppDatabase
import com.katfox.movieappsimple.feature_movie.data.remote.AuthorizationInterceptor
import com.katfox.movieappsimple.feature_movie.data.remote.MovieApi
import com.katfox.movieappsimple.feature_movie.data.repository.MovieRepositoryImpl
import com.katfox.movieappsimple.feature_movie.domain.repository.MovieRepository
import com.katfox.movieappsimple.feature_movie.domain.use_case.GetMovieInfos
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieInfoModule {

    @Qualifier
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    internal annotation class BaseUrl

    @Provides
    @Reusable
    @BaseUrl
    fun provideBaseUrl() = "https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun provideGetMovieInfoUseCase(repository: MovieRepository): GetMovieInfos {
        return GetMovieInfos(repository)
    }

    @Provides
    @Singleton
    fun provideMovieInfoRepository(
        api: MovieApi,
        db: MovieAppDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(api, db.movieInfoDao)
    }

    @Provides
    @Singleton
    fun provideMovieAppDatabase(app: Application): MovieAppDatabase {
        return Room.databaseBuilder(
            app, MovieAppDatabase::class.java, "movie_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String
    ): MovieApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

}