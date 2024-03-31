package com.aherbel.cryptotracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aherbel.cryptotracker.domain.MarketDataRepository
import com.aherbel.cryptotracker.domain.PercentageFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val marketDataRepository: MarketDataRepository,
    private val percentageFormatter: PercentageFormatter
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(initialMainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun requestMarketData() {
        viewModelScope.launch {
            marketDataRepository.marketData()
                .collect { marketData ->
                    val marketCapVariation = marketData.marketCapVariation
                    val marketDataUi = MarketDataUi(
                        marketCapValue = "$%.2fTr".format(marketData.marketCapValue),
                        marketCapVariation = percentageFormatter.format(marketCapVariation),
                        marketCapVariationIsPositive = marketCapVariation > 0
                    )
                    updateUiState { uiState ->
                        uiState.copy(marketDataUi = marketDataUi)
                    }
                }
        }
    }

    private suspend fun updateUiState(block: (MainUiState) -> MainUiState) {
        _uiState.emit(block(_uiState.value))
    }

    private fun initialMainUiState(): MainUiState = MainUiState(
        marketDataUi = initialMarketDataUi()
    )

    private fun initialMarketDataUi(): MarketDataUi = MarketDataUi(
        marketCapValue = "",
        marketCapVariation = "",
        marketCapVariationIsPositive = false
    )
}

data class MainUiState(
    val marketDataUi: MarketDataUi
)

data class MarketDataUi(
    val marketCapValue: String,
    val marketCapVariation: String,
    val marketCapVariationIsPositive: Boolean
)