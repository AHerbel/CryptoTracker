package com.aherbel.cryptotracker.domain

import kotlinx.coroutines.flow.Flow

interface MarketDataNetworkDataSource {

    suspend fun getMarketData(): Flow<MarketData>

}
