package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.network.CoinMarketCapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    @Provides
    fun providesRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesCoinMarketCapService(retrofit: Retrofit): CoinMarketCapService = retrofit.create()

}