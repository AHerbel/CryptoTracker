package com.aherbel.cryptotracker

import app.cash.turbine.test
import com.aherbel.cryptotracker.ui.main.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun `test request market data at init`() = runTest {
        val mainViewModel = MainViewModel(FakeMarketDataRepository())

        mainViewModel.uiState.test {
            val item = awaitItem()
            assertNotNull(item)
        }
    }
}