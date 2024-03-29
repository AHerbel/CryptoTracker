package com.aherbel.cryptotracker.application.datasources

import com.aherbel.cryptotracker.application.network.CoinMarketCapService
import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataNetworkDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CoinMarketCapMarketDataNetworkDataSource @Inject constructor(
    private val coinMarketCapService : CoinMarketCapService
) : MarketDataNetworkDataSource {

    override suspend fun getMarketData(): Flow<MarketData> = callbackFlow {
        val globalMetricsResponse = coinMarketCapService.getLatestGlobalMetrics()
        val data = globalMetricsResponse.data

        send(
            MarketData(
                marketCapValue = data.quote.usd.totalMarketCap,
                marketCapVariation = data.btcDominance24hPercentageChange
            )
        )

        awaitClose()
    }
}