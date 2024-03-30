package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.di.qualifiers.ApiKey
import com.aherbel.cryptotracker.application.di.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkConfigurationModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = "https://pro-api.coinmarketcap.com/"

    @Provides
    @Singleton
    @ApiKey
    fun providesApiKey(): String = "c9a55b9f-32db-49aa-9abd-b51409bfffa2"

}