package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.ui.main.MarketDataUi

data class MarketDataUiBuilder(
    private var marketCapValue: String = "",
    private var marketCapVariation: String = "",
    private var marketCapVariationIsPositive: Boolean = false
) {

    companion object {
        fun aMarketDataUi(): MarketDataUiBuilder = MarketDataUiBuilder()
    }

    fun withMarketCapValue(marketCapValue: String): MarketDataUiBuilder {
        return copy(marketCapValue = marketCapValue)
    }

    fun withMarketCapVariation(marketCapVariation: String): MarketDataUiBuilder {
        return copy(marketCapVariation = marketCapVariation)
    }

    fun withMarketCapVariationIsPositive(marketCapVariationIsPositive: Boolean): MarketDataUiBuilder {
        return copy(marketCapVariationIsPositive = marketCapVariationIsPositive)
    }

    fun build(): MarketDataUi = MarketDataUi(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        marketCapVariationIsPositive = marketCapVariationIsPositive
    )

}