package com.aherbel.cryptotracker.domain

import kotlinx.coroutines.flow.Flow

interface MarketDataRepository {

    suspend fun getMarketData(): Flow<MarketData>

}
