package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.repositories.CoinMarketCapMarketDataRepository
import com.aherbel.cryptotracker.domain.MarketDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMarketDataRepository(
        coinMarketCapMarketDataRepository: CoinMarketCapMarketDataRepository
    ) : MarketDataRepository

}