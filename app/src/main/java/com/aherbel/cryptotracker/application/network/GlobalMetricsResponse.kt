package com.aherbel.cryptotracker.application.network

import com.google.gson.annotations.SerializedName

data class GlobalMetricsResponse(
    @SerializedName("btc_dominance")
    val btcDominance: Double,
    @SerializedName("btc_dominance_24h_percentage_change")
    val btcDominance24hPercentageChange: Double,
    val quote: Quote
)

data class Quote(
    @SerializedName("USD")
    val usd: USD
)

data class USD(
    @SerializedName("total_market_cap")
    val totalMarketCap: Double,
    @SerializedName("total_volume_24h")
    val totalVolume24h: Double
)
