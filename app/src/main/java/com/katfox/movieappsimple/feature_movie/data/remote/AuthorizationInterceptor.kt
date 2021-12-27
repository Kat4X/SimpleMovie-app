package com.katfox.movieappsimple.feature_movie.data.remote

import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Reusable
class AuthorizationInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val API_KEY_HEADER = "api_key"
        private const val API_KEY = "11bffaa85f8d8ad5f2b4678dfca183d2"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY_HEADER, API_KEY).build()
        val authenticatedRequest = request.newBuilder().url(url).build()

        return chain.proceed(authenticatedRequest)
    }
}