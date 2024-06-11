package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.MarketData

data class MarketDataBuilder(
    private var marketCapValue: Double = 0.0,
    private var marketCapVariation: Double = 0.0,
    private var twentyFourHourVolume: Double = 0.0
) {

    companion object {
        fun aMarketData(): MarketDataBuilder = MarketDataBuilder()
    }

    fun withMarketCapValue(marketCapValue: Double): MarketDataBuilder {
        return copy(marketCapValue = marketCapValue)
    }

    fun withMarketCapVariation(marketCapVariation: Double): MarketDataBuilder {
        return copy(marketCapVariation = marketCapVariation)
    }

    fun with24HsVolume(twentyFourHourVolume: Double): MarketDataBuilder {
        return copy(twentyFourHourVolume = twentyFourHourVolume)
    }

    fun build(): MarketData = MarketData(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        twentyFourHourVolume = twentyFourHourVolume
    )
}