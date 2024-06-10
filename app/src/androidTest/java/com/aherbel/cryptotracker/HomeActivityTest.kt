package com.aherbel.cryptotracker

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.aherbel.cryptotracker.application.di.NetworkConfigurationModule
import com.aherbel.cryptotracker.infra.FakeServer
import com.aherbel.cryptotracker.infra.FakeServerRule
import com.aherbel.cryptotracker.infra.robots.homeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
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

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun openHomeScreen_whenStarted_displaysTitle() {
        fakeServer.willAnswerDefaultMarketDataInformation()

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysTitle("Live Prices")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displaysPositiveMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithMarketCapPercentageChange(10)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysPositiveMarketCapVariation("10%")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displaysNegativeMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithMarketCapPercentageChange(-10)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysNegativeMarketCapVariation("-10%")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInThousands() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000.0)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysMarketCapValue("$1.23M")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInBillions() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000000.0)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysMarketCapValue("$1.23B")
        }
    }

    @Test
    fun openHomeScreen_whenStarted_displayFormattedMarketCapValueInTrillions() {
        fakeServer.willAnswerMarketDataWithMarketCapValueOf(1230000000000.0)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysMarketCapValue("$1.23T")
        }
    }

    @Test
    fun openHome_whenStarted_displays24HSVolume() {
        fakeServer.willAnswer24HsVolume(262240000000.0)

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displays24HsVolume("$262.24B")
        }
    }
}