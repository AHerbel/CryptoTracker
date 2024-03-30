package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.application.di.UrlModule
import com.aherbel.cryptotracker.application.di.qualifiers.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UrlModule::class]
)
class TestUrlModule {

    @Provides
    @Singleton
    fun providesBaseUrl(): String = "http://127.0.0.1:8080"

    @Provides
    @Singleton
    @ApiKey
    fun providesApiKey(): String = "testApiKey"
}