package com.aherbel.cryptotracker.application.network

import com.aherbel.cryptotracker.application.di.qualifiers.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddApiKeyHeaderInterceptor @Inject constructor(
    @ApiKey private val apiKey: String
) : Interceptor {

    companion object {
        const val HEADER_API_KEY = "X-CMC_PRO_API_KEY"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader(HEADER_API_KEY, apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}