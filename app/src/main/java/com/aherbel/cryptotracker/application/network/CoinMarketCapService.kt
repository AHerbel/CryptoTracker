package com.aherbel.cryptotracker.application.network

import retrofit2.http.GET

interface CoinMarketCapService {

    @GET("v1/global-metrics/quotes/latest")
    suspend fun getLatestGlobalMetrics(): Response<GlobalMetricsResponse>

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getLatestCoins(): Response<CoinsResponse>

}
