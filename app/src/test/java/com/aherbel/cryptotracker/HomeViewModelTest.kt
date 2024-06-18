package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.HomeUiStateBuilder.Companion.aMainUiState
import com.aherbel.cryptotracker.MarketDataBuilder.Companion.aMarketData
import com.aherbel.cryptotracker.application.DecimalPercentageFormatter
import com.aherbel.cryptotracker.ui.home.HomeViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull

class HomeViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val fakeMarketDataRepository = FakeMarketDataRepository()
    private val mainViewModel = HomeViewModel(fakeMarketDataRepository, DecimalPercentageFormatter(), USCurrencyFormatter())

    @Test
    fun `asking for MarketData the first time returns initial state`() = runTest {
        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.firstOrNull()

        expectThat(mainUiState)
            .isNotNull()
            .isEqualTo(aMainUiState().build())
    }

    @Test
    fun `marketCapVariation is formatted with 2 decimal places`() = runTest {
        fakeMarketDataRepository.items = listOf(
            aMarketData()
                .withMarketCapVariation(10.1234)
                .build()
        )

        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.firstOrNull()

        expectThat(mainUiState)
            .describedAs("initial HomeUiState")
            .isNotNull()
            .and {
                get { marketDataUi.marketCapVariation }
                    .describedAs("marketCapVariation")
                    .isEqualTo("10.12%")
            }
    }

    @Test
    fun `asking for MarketData returns not available when error happens`() = runTest {
        fakeMarketDataRepository.throwError = true

        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.firstOrNull()

        expectThat(mainUiState)
            .describedAs("initial HomeUiState")
            .isNotNull()
            .and {
                get { marketDataUi }
                    .describedAs("marketDataUi")
                    .and {
                        get { marketCapVariation }
                            .describedAs("marketCapVariation")
                            .isEqualTo("")
                        get { marketCapValue }
                            .describedAs("marketCapValue")
                            .isEqualTo("N/A")
                        get { btcDominance }
                            .describedAs("btcDominance")
                            .isEqualTo("N/A")
                        get { twentyFourHsVolume }
                            .describedAs("24HsVolume")
                            .isEqualTo("N/A")
                    }
            }
    }
}