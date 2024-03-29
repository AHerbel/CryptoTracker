package com.aherbel.cryptotracker.application.repositories

import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataNetworkDataSource
import com.aherbel.cryptotracker.domain.MarketDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinMarketCapMarketDataRepository @Inject constructor(
    private val marketDataNetworkDataSource : MarketDataNetworkDataSource
) : MarketDataRepository {

    override suspend fun getMarketData(): Flow<MarketData> =
        marketDataNetworkDataSource.getMarketData()
}