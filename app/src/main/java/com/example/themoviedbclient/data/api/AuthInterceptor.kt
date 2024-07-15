package com.example.themoviedbclient.data.api

import com.example.themoviedbclient.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.TMDB_TOKEN}")
            .build()

        return chain.proceed(modifiedRequest)
    }
}