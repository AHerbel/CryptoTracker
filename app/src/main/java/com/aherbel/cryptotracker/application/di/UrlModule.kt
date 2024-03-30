package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.di.qualifiers.ApiKey
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
    fun provideBaseUrl(): String = "https://pro-api.coinmarketcap.com/"

    @Provides
    @Singleton
    @ApiKey
    fun providesApiKey(): String = "c9a55b9f-32db-49aa-9abd-b51409bfffa2"

}