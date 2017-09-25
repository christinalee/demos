package com.examples.lee.networking

import okhttp3.Interceptor
import okhttp3.Response

// TODO: Dagger AF
object HeaderInterceptor: Interceptor {
    private const val CLIENT_ID = "INSERT CLIENT ID HERE"
    override fun intercept(chain: Interceptor.Chain): Response {
        val headerRequest = chain.request().newBuilder()
                .addHeader("Accept-Version", "v1")
                .addHeader("Authorization", "Client-ID $CLIENT_ID")
                .build()
        return chain.proceed(headerRequest)
    }
}

