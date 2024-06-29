package com.aherbel.cryptotracker.builders

import com.aherbel.cryptotracker.ui.home.MarketDataUi
import com.aherbel.cryptotracker.ui.text.PlainUiText
import com.aherbel.cryptotracker.ui.text.UiText

data class MarketDataUiBuilder(
    private var marketCapValue: UiText = PlainUiText(""),
    private var marketCapVariation: UiText = PlainUiText(""),
    private var marketCapVariationIsPositive: Boolean = false,
    private var twentyFourHsVolume: UiText = PlainUiText(""),
    private var btcDominance: UiText = PlainUiText("")
) {

    companion object {
        fun aMarketDataUi(): MarketDataUiBuilder = MarketDataUiBuilder()
    }

    fun withMarketCapValue(marketCapValue: UiText): MarketDataUiBuilder {
        return copy(marketCapValue = marketCapValue)
    }

    fun withMarketCapVariation(marketCapVariation: UiText): MarketDataUiBuilder {
        return copy(marketCapVariation = marketCapVariation)
    }

    fun withPositiveMarketCapVariation(): MarketDataUiBuilder {
        return copy(marketCapVariationIsPositive = true)
    }

    fun withNegativeMarketCapVariation(): MarketDataUiBuilder {
        return copy(marketCapVariationIsPositive = false)
    }

    fun with24HsVolume(twentyFourHsVolume: UiText): MarketDataUiBuilder {
        return copy(twentyFourHsVolume = twentyFourHsVolume)
    }

    fun withBtcDominance(btcDominance: UiText): MarketDataUiBuilder {
        return copy(btcDominance = btcDominance)
    }

    fun build(): MarketDataUi = MarketDataUi(
        marketCapValue = marketCapValue,
        marketCapVariation = marketCapVariation,
        marketCapVariationIsPositive = marketCapVariationIsPositive,
        twentyFourHsVolume = twentyFourHsVolume,
        btcDominance = btcDominance
    )

}