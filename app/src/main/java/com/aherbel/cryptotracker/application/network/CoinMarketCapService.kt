package com.aherbel.cryptotracker.application.network

import retrofit2.http.GET

interface CoinMarketCapService {

    @GET("/global-metrics/quotes/latest")
    suspend fun getLatestGlobalMetrics(): Response<GlobalMetricsResponse>

}
