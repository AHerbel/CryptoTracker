package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.HomeUiStateBuilder.Companion.aMainUiState
import com.aherbel.cryptotracker.MarketDataBuilder.Companion.aMarketData
import com.aherbel.cryptotracker.application.DecimalPercentageFormatter
import com.aherbel.cryptotracker.ui.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private val fakeMarketDataRepository = FakeMarketDataRepository()
    private val mainViewModel = HomeViewModel(fakeMarketDataRepository, DecimalPercentageFormatter(), USCurrencyFormatter())

    @Test
    fun `test request market data emits initial state as first item when requested`() = runTest {
        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.first()
        val expected = aMainUiState().build()
        assertNotNull("Initial state is null. It should be $expected", mainUiState)
        assertEquals(
            "Initial state is different from expected.",
            expected,
            mainUiState
        )
    }

    @Test
    fun `test marketCapVariation get formatted with 2 decimal`() = runTest {
        fakeMarketDataRepository.items = listOf(
            aMarketData().withMarketCapVariation(10.1234).build()
        )

        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.first()
        val marketCapVariation = mainUiState.marketDataUi.marketCapVariation
        val expected = "10.12%"
        assertEquals(
            "MarketCapVariation was formatted incorrectly.",
            expected,
            marketCapVariation
        )
    }
}