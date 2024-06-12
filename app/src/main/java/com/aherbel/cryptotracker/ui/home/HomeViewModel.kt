package com.aherbel.cryptotracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aherbel.cryptotracker.domain.CurrencyFormatter
import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataRepository
import com.aherbel.cryptotracker.domain.PercentageFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marketDataRepository: MarketDataRepository,
    private val percentageFormatter: PercentageFormatter,
    private val currencyFormatter: CurrencyFormatter
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(initialMainUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun requestMarketData() {
        viewModelScope.launch {
            marketDataRepository.marketData()
                .collect { marketData ->
                    updateUiState { uiState ->
                        uiState.copy(marketDataUi = mapToUi(marketData))
                    }
                }
        }
    }

    private fun mapToUi(marketData: MarketData): MarketDataUi {
        val marketCapVariation = marketData.marketCapVariation
        return MarketDataUi(
            marketCapValue = currencyFormatter.format(marketData.marketCapValue),
            marketCapVariation = percentageFormatter.format(marketCapVariation),
            marketCapVariationIsPositive = marketCapVariation > 0,
            twentyFourHsVolume = currencyFormatter.format(marketData.twentyFourHourVolume)
        )
    }

    private suspend fun updateUiState(block: (HomeUiState) -> HomeUiState) {
        _uiState.emit(block(_uiState.value))
    }

    private fun initialMainUiState(): HomeUiState = HomeUiState(
        marketDataUi = initialMarketDataUi()
    )

    private fun initialMarketDataUi(): MarketDataUi = MarketDataUi(
        marketCapValue = "",
        marketCapVariation = "",
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = ""
    )
}

data class HomeUiState(
    val marketDataUi: MarketDataUi
)

data class MarketDataUi(
    val marketCapValue: String,
    val marketCapVariation: String,
    val marketCapVariationIsPositive: Boolean,
    val twentyFourHsVolume: String
)