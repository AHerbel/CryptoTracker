package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.MarketDataUiBuilder.Companion.aMarketDataUi
import com.aherbel.cryptotracker.ui.main.MainUiState
import com.aherbel.cryptotracker.ui.main.MarketDataUi

class MainUiStateBuilder private constructor(
    private var marketDataUi: MarketDataUi = aMarketDataUi().build()
) {

    companion object {
        fun aMainUiState(): MainUiStateBuilder = MainUiStateBuilder()
    }

    private fun new(): MainUiStateBuilder = MainUiStateBuilder(marketDataUi)

    fun with(marketDataUi: MarketDataUi): MainUiStateBuilder {
        this.marketDataUi = marketDataUi
        return new()
    }

    fun build(): MainUiState = MainUiState(marketDataUi = marketDataUi)

}