package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.MarketDataUiBuilder.Companion.aMarketDataUi
import com.aherbel.cryptotracker.ui.home.HomeUiState
import com.aherbel.cryptotracker.ui.home.MarketDataUi

data class HomeUiStateBuilder(
    private var marketDataUi: MarketDataUi = aMarketDataUi().build()
) {

    companion object {
        fun aMainUiState(): HomeUiStateBuilder = HomeUiStateBuilder()
    }

    fun with(marketDataUi: MarketDataUiBuilder): HomeUiStateBuilder {
        return copy(marketDataUi = marketDataUi.build())
    }

    fun build(): HomeUiState = HomeUiState(marketDataUi = marketDataUi)
}