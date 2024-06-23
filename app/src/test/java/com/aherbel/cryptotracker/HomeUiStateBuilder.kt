package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.MarketDataUiBuilder.Companion.aMarketDataUi
import com.aherbel.cryptotracker.ui.home.HomeUiState
import com.aherbel.cryptotracker.ui.home.MarketDataUi

data class HomeUiStateBuilder(
    private var marketDataUi: MarketDataUi = aMarketDataUi().build(),
    private var isLoading: Boolean = false
) {

    companion object {
        fun aMainUiState(): HomeUiStateBuilder = HomeUiStateBuilder()
    }

    fun with(marketDataUi: MarketDataUiBuilder): HomeUiStateBuilder {
        return copy(marketDataUi = marketDataUi.build())
    }

    fun thatIsLoading(): HomeUiStateBuilder {
        return copy(isLoading = true)
    }

    fun build(): HomeUiState = HomeUiState(marketDataUi = marketDataUi, isLoading = isLoading)
}