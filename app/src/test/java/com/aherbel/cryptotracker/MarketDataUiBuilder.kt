package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.ui.main.MarketDataUi

class MarketDataUiBuilder private constructor(
    private var marketCapValue: String = "",
    private var marketCapVariation: String = "",
    private var marketCapVariationIsPositive: Boolean = false
) {

    companion object {
        fun aMarketDataUi(): MarketDataUiBuilder = MarketDataUiBuilder()
    }

    private fun new(): MarketDataUiBuilder = MarketDataUiBuilder(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        marketCapVariationIsPositive = marketCapVariationIsPositive
    )

    fun withMarketCapValue(marketCapValue: String): MarketDataUiBuilder {
        this.marketCapValue = marketCapValue
        return new()
    }

    fun withMarketCapVariation(marketCapVariation: String): MarketDataUiBuilder {
        this.marketCapVariation = marketCapVariation
        return new()
    }

    fun withMarketCapVariationIsPositive(marketCapVariationIsPositive: Boolean): MarketDataUiBuilder {
        this.marketCapVariationIsPositive = marketCapVariationIsPositive
        return new()
    }

    fun build(): MarketDataUi = MarketDataUi(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        marketCapVariationIsPositive = marketCapVariationIsPositive
    )

}