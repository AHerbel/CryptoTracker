package com.aherbel.cryptotracker.application.network

import com.google.gson.annotations.SerializedName

data class GlobalMetricsResponse(val quote: Quote)

data class Quote(
    @SerializedName("USD")
    val currency: Currency
)

data class Currency(
    @SerializedName("total_market_cap")
    val totalMarketCap: Double,
    @SerializedName("total_market_cap_yesterday_percentage_change")
    val totalMarketCapYesterdayPercentageChange: Double
)
