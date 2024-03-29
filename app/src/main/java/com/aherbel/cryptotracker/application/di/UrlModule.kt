package com.aherbel.cryptotracker.application.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UrlModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://pro-api.coinmarketcap.com/v1/"

}