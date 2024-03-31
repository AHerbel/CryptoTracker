package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMarketDataRepository : MarketDataRepository {

    var items: List<MarketData> = emptyList()

    override suspend fun getMarketData(): Flow<MarketData> = flow {
        items.forEach {
            emit(it)
        }
    }
}
