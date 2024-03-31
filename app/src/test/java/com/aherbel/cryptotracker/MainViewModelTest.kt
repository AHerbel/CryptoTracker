package com.aherbel.cryptotracker

import app.cash.turbine.test
import com.aherbel.cryptotracker.MainUiStateBuilder.Companion.aMainUiState
import com.aherbel.cryptotracker.ui.main.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun `test request market data emits initial state as first item when requested`() = runTest {
        val mainViewModel = MainViewModel(FakeMarketDataRepository())

        mainViewModel.collectMarketData()

        mainViewModel.uiState.test {
            val mainUiState = awaitItem()
            val expected = aMainUiState().build()
            assertNotNull(mainUiState)
            assertEquals(expected, mainUiState)
        }
    }
}