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
    fun requestMarketData_atFirstRequest_emitsInitialState() = runTest {
        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.firstOrNull()

        expectThat(mainUiState)
            .isNotNull()
            .isEqualTo(aMainUiState().build())
    }

    @Test
    fun requestInitialMarketData_onMarketCapVariation_formatsItWith2DecimalPlaces() = runTest {
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
}