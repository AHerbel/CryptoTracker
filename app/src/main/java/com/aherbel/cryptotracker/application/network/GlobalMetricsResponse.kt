package com.aherbel.cryptotracker.application.network

import com.google.gson.annotations.SerializedName

data class GlobalMetricsResponse(val quote: Quote)

data class Quote(
    @SerializedName("USD")
    val usd: USD
)

data class USD(
    @SerializedName("total_market_cap")
    val totalMarketCap: Double,
    @SerializedName("total_market_cap_yesterday_percentage_change")
    val totalMarketCapYesterdayPercentageChange: Double
)
