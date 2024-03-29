package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.datasources.CoinMarketCapMarketDataNetworkDataSource
import com.aherbel.cryptotracker.application.network.CoinMarketCapService
import com.aherbel.cryptotracker.domain.MarketDataNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesMarketDataNetworkDataSource(
        coinMarketCapService: CoinMarketCapService
    ): MarketDataNetworkDataSource = CoinMarketCapMarketDataNetworkDataSource(coinMarketCapService)

}