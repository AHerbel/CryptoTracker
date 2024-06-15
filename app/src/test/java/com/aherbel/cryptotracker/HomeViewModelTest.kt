package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.HomeUiStateBuilder.Companion.aMainUiState
import com.aherbel.cryptotracker.MarketDataBuilder.Companion.aMarketData
import com.aherbel.cryptotracker.application.DecimalPercentageFormatter
import com.aherbel.cryptotracker.ui.home.HomeUiState
import com.aherbel.cryptotracker.ui.home.HomeViewModel
import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.present
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val fakeMarketDataRepository = FakeMarketDataRepository()
    private val mainViewModel = HomeViewModel(fakeMarketDataRepository, DecimalPercentageFormatter(), USCurrencyFormatter())

    @Test
    fun requestMarketData_atFirstRequest_emitsInitialState() = runTest {
        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.first()

        assertThat(
            mainUiState,
            present<HomeUiState>() and equalTo(aMainUiState().build())
        )
    }

    @Test
    fun requestInitialMarketData_onMarketCapVariation_formatsItWith2DecimalPlaces() = runTest {
        fakeMarketDataRepository.items = listOf(
            aMarketData()
                .withMarketCapVariation(10.1234)
                .build()
        )

        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.first()
        val marketCapVariation = mainUiState.marketDataUi.marketCapVariation

        assertThat(marketCapVariation, equalTo("10.12%"))
    }
}