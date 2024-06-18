package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMarketDataRepository : MarketDataRepository {

    var throwError: Boolean = false
    var items: List<MarketData> = emptyList()

    override fun marketData(): Flow<MarketData> = flow {
        if (throwError) throw Exception()
        items.forEach {
            emit(it)
        }
    }
}
