package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.BuildConfig
import com.aherbel.cryptotracker.application.di.qualifiers.BaseUrl
import com.aherbel.cryptotracker.application.network.AddApiKeyHeaderInterceptor
import com.aherbel.cryptotracker.application.network.CoinMarketCapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @Provides
    fun providesOkHttpClient(
        addApiKeyHeaderInterceptor: AddApiKeyHeaderInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(interceptor)
            }
        }
        .addInterceptor(addApiKeyHeaderInterceptor)
        .build()

    @Provides
    fun providesRetrofit(
        @BaseUrl baseUrl: String,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesCoinMarketCapService(retrofit: Retrofit): CoinMarketCapService = retrofit.create()

}