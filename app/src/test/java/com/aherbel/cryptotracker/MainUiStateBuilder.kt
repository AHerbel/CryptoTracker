package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.MarketDataUiBuilder.Companion.aMarketDataUi
import com.aherbel.cryptotracker.ui.main.MainUiState
import com.aherbel.cryptotracker.ui.main.MarketDataUi

data class MainUiStateBuilder(
    private var marketDataUi: MarketDataUi = aMarketDataUi().build()
) {

    companion object {
        fun aMainUiState(): MainUiStateBuilder = MainUiStateBuilder()
    }

    fun with(marketDataUi: MarketDataUiBuilder): MainUiStateBuilder {
        return copy(marketDataUi = marketDataUi.build())
    }

    fun build(): MainUiState = MainUiState(marketDataUi = marketDataUi)
}