package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.application.DecimalPercentageFormatter
import com.aherbel.cryptotracker.builders.AndroidResourceUiTextDataBuilder.Companion.anAndroidResourceUiText
import com.aherbel.cryptotracker.builders.HomeUiStateBuilder.Companion.aMainUiState
import com.aherbel.cryptotracker.builders.MarketDataBuilder.Companion.aMarketData
import com.aherbel.cryptotracker.builders.PlainUiTextDataBuilder.Companion.aPlainUiText
import com.aherbel.cryptotracker.ui.home.HomeViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isNotNull
import strikt.assertions.isTrue

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
            .isEqualTo(aMainUiState().thatIsLoading().build())
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
                    .isEqualTo(aPlainUiText().withText("10.12%").build())
            }
    }

    @Test
    fun `asking for MarketData returns not available when error happens`() = runTest {
        fakeMarketDataRepository.throwError = true

        mainViewModel.requestMarketData()

        val mainUiState = mainViewModel.uiState.firstOrNull()
        val notAvailableText = anAndroidResourceUiText().withTextRes(R.string.not_available).build()

        expectThat(mainUiState)
            .describedAs("initial HomeUiState")
            .isNotNull()
            .and {
                get { marketDataUi }
                    .describedAs("marketDataUi")
                    .and {
                        get { marketCapVariation }
                            .describedAs("marketCapVariation")
                            .isEqualTo(aPlainUiText().build())
                        get { marketCapValue }
                            .describedAs("marketCapValue")
                            .isEqualTo(notAvailableText)
                        get { btcDominance }
                            .describedAs("btcDominance")
                            .isEqualTo(notAvailableText)
                        get { twentyFourHsVolume }
                            .describedAs("24HsVolume")
                            .isEqualTo(notAvailableText)
                    }
            }
    }

    @Test
    fun `asking for MarketData shows loading while data is being fetched`() = runTest {
        mainViewModel.requestMarketData()

        val isLoading = mainViewModel.uiState.firstOrNull()?.isLoading

        expectThat(isLoading)
            .describedAs("isLoading")
            .isNotNull()
            .isTrue()
    }

    @Test
    fun `loading is hidden after fetching data`() = runTest {
        fakeMarketDataRepository.items = listOf(aMarketData().build())

        mainViewModel.requestMarketData()

        val isLoading = mainViewModel.uiState.firstOrNull()?.isLoading

        expectThat(isLoading)
            .describedAs("isLoading")
            .isNotNull()
            .isFalse()
    }

    @Test
    fun `loading is hidden after error when fetching data`() = runTest {
        fakeMarketDataRepository.throwError = true

        mainViewModel.requestMarketData()

        val isLoading = mainViewModel.uiState.firstOrNull()?.isLoading

        expectThat(isLoading)
            .describedAs("isLoading")
            .isNotNull()
            .isFalse()
    }
}