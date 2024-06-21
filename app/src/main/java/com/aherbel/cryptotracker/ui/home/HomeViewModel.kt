package com.aherbel.cryptotracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aherbel.cryptotracker.R
import com.aherbel.cryptotracker.domain.CurrencyFormatter
import com.aherbel.cryptotracker.domain.MarketData
import com.aherbel.cryptotracker.domain.MarketDataRepository
import com.aherbel.cryptotracker.domain.PercentageFormatter
import com.aherbel.cryptotracker.ui.text.AndroidResourceUiText
import com.aherbel.cryptotracker.ui.text.PlainUiText
import com.aherbel.cryptotracker.ui.text.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
            marketDataRepository
                .marketData()
                .catch {
                    updateUiState { uiState ->
                        uiState.copy(marketDataUi = errorMarketDataUi())
                    }
                }
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
            marketCapValue = PlainUiText(currencyFormatter.format(marketData.marketCapValue)),
            marketCapVariation = PlainUiText(percentageFormatter.format(marketCapVariation)),
            marketCapVariationIsPositive = marketCapVariation > 0,
            twentyFourHsVolume = PlainUiText(currencyFormatter.format(marketData.twentyFourHourVolume)),
            btcDominance = PlainUiText(
                percentageFormatter.apply {
                    setMinPrecisionDigits(2)
                }.format(marketData.btcDominance)
            )
        )
    }

    private suspend fun updateUiState(block: (HomeUiState) -> HomeUiState) {
        _uiState.emit(block(_uiState.value))
    }

    private fun initialMainUiState(): HomeUiState = HomeUiState(
        marketDataUi = initialMarketDataUi()
    )

    private fun errorMarketDataUi(): MarketDataUi = MarketDataUi(
        marketCapValue = AndroidResourceUiText(R.string.not_available),
        marketCapVariation = PlainUiText(""),
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = AndroidResourceUiText(R.string.not_available),
        btcDominance = AndroidResourceUiText(R.string.not_available)
    )

    private fun initialMarketDataUi(): MarketDataUi = MarketDataUi(
        marketCapValue = PlainUiText(""),
        marketCapVariation = PlainUiText(""),
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = PlainUiText(""),
        btcDominance = PlainUiText("")
    )
}

data class HomeUiState(
    val marketDataUi: MarketDataUi
)

data class MarketDataUi(
    val marketCapValue: UiText,
    val marketCapVariation: UiText,
    val marketCapVariationIsPositive: Boolean,
    val twentyFourHsVolume: UiText,
    val btcDominance: UiText
)