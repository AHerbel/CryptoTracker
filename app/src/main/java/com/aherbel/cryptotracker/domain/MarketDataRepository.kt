package com.aherbel.cryptotracker.domain

import kotlinx.coroutines.flow.Flow

interface MarketDataRepository {

    fun marketData(): Flow<MarketData>
}
