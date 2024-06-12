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

    override fun marketData(): Flow<MarketData> = callbackFlow {
        val globalMetricsResponse = coinMarketCapService.getLatestGlobalMetrics().data
        val currency = globalMetricsResponse.quote.currency

        send(
            MarketData(
                marketCapValue = currency.totalMarketCap,
                marketCapVariation = currency.totalMarketCapYesterdayPercentageChange,
                twentyFourHourVolume = currency.totalVolume24h,
                btcDominance = globalMetricsResponse.btcDominance
            )
        )

        awaitClose()
    }
}