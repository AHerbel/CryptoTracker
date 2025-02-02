package com.aherbel.cryptotracker

import androidx.compose.ui.test.junit4.createComposeRule
import com.aherbel.cryptotracker.application.di.NetworkConfigurationModule
import com.aherbel.cryptotracker.infra.FakeApplication
import com.aherbel.cryptotracker.infra.FakeServer
import com.aherbel.cryptotracker.infra.FakeServerRule
import com.aherbel.cryptotracker.infra.robots.homeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


@HiltAndroidTest
@UninstallModules(NetworkConfigurationModule::class)
class HomeActivityTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @get:Rule(order = 2)
    val fakeServerRule = FakeServerRule()

    private val fakeServer: FakeServer get() = fakeServerRule.fakeServer
    private val fakeApplication = FakeApplication()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun openHomeScreen_whenStarted_displaysTitle() {
        fakeServer.willAnswerDefaultMarketDataInformation()

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysTitle("Live Prices")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displaysPositiveMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithMarketCapPercentageChange(10)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysPositiveMarketCapVariationOf("10%")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displaysNegativeMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithMarketCapPercentageChange(-10)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysNegativeMarketCapVariationOf("-10%")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInThousands() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000.0)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysMarketCapValueOf("$1.23M")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInBillions() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000000.0)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysMarketCapValueOf("$1.23B")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInTrillions() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000000000.0)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysMarketCapValueOf("$1.23T")
        }
    }

    @Test
    fun openHome_whenStarted_displays24HSVolume() {
        fakeServer.willAnswer24HsVolume(262240000000.0)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displays24HsVolumeOf("$262.24B")
        }
    }

    @Test
    fun openHome_whenStarted_displaysBTCDominance() {
        fakeServer.willAnswerBTCDominance(45)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysBTCDominanceOf("45.00%")
        }
    }

    @Test
    fun openHome_whenErrorOnMarketData_displaysNotAvailableMarketData() {
        fakeServer.willAnswerError400OnMarketData()

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysNotAvailableMarketCap()
            displaysNotAvailable24HsVolume()
            displaysNotAvailableBtcDominance()
            doesNotDisplayMarketCapVariation()
        }
    }

    @Test
    fun onPullToRefresh_whenStarted_displaysLoading() {
        fakeServer.willAnswerDefaultMarketDataInformation()
        fakeServer.willAnswerDefaultMarketDataInformationWithDelayOf(500, TimeUnit.MILLISECONDS)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displays24HsVolumeOf("$0.00")
            displaysMarketCapValueOf("$0.00")
            displaysBTCDominanceOf("0.00%")
            displaysNegativeMarketCapVariationOf("0%")

            performPullToRefresh()

            displaysLoading()
        }
    }

    @Test
    fun onPullToRefresh_whenFinished_hidesLoading() = runTest {
        fakeServer.willAnswerDefaultMarketDataInformation()
        fakeServer.willAnswerDefaultMarketDataInformationWithDelayOf(100, TimeUnit.MILLISECONDS)

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displays24HsVolumeOf("$0.00")
            displaysMarketCapValueOf("$0.00")
            displaysBTCDominanceOf("0.00%")
            displaysNegativeMarketCapVariationOf("0%")

            performPullToRefresh()
            displaysLoading()

            fakeApplication.delayBy(300)
            hidesLoading()
        }
    }

    @Test
    fun openHome_whenStarted_displaysCoinsList() {
        fakeServer.willAnswerDefaultCoinsList()

        fakeApplication.launchHomeScreen()

        homeScreen(composeRule) {
            displaysCoins("BTC", "ETH", "USDT")
        }
    }
}