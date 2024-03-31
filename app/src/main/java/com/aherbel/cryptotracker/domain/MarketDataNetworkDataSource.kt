package com.aherbel.cryptotracker.domain

import kotlinx.coroutines.flow.Flow

interface MarketDataNetworkDataSource {

    fun marketData(): Flow<MarketData>
}
