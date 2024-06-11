package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.ui.home.MarketDataUi

data class MarketDataUiBuilder(
    private var marketCapValue: String = "",
    private var marketCapVariation: String = "",
    private var marketCapVariationIsPositive: Boolean = false,
    private var twentyFourHsVolume: String = ""
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

    fun with24HsVolume(twentyFourHsVolume: String): MarketDataUiBuilder {
        return copy(twentyFourHsVolume = twentyFourHsVolume)
    }

    fun build(): MarketDataUi = MarketDataUi(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        marketCapVariationIsPositive = marketCapVariationIsPositive,
        twentyFourHsVolume = twentyFourHsVolume
    )

}