package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMarketDataRepository : MarketDataRepository {
    override suspend fun getMarketData(): Flow<MarketData> = flowOf()
}
