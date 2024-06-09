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
    fun open_homeScreen_displaysHeader() {
        fakeServer.willAnswerDefaultMarketDataInformation()

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysHeader()
        }
    }

    @Test
    fun open_homeScreen_displaysMarketData() {
        fakeServer.willAnswerMarketDataInformation()

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysMarketData()
            displaysMarketCap("$123.00Tr", "-16.08%")
        }
    }

    @Test
    fun open_homeScreen_displaysPositiveMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithPositiveMarketCapPercentageChange()

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysPositiveMarketCapVariation("10%")
        }
    }

    @Test
    fun open_homeScreen_displaysNegativeMarketCapVariation() = runTest {
        fakeServer.willAnswerMarketDataWithNegativeMarketCapPercentageChange()

        ActivityScenario.launch(HomeActivity::class.java)

        homeScreen(composeRule) {
            displaysNegativeMarketCapVariation("-10%")
        }
    }
}