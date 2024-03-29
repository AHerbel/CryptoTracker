package com.aherbel.cryptotracker

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.aherbel.cryptotracker.application.di.UrlModule
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
@UninstallModules(UrlModule::class)
class MainActivityTest {

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
    fun testHeaderIsDisplayed() {
        fakeServer.globalMetricsDefaultResponse()

        ActivityScenario.launch(MainActivity::class.java)

        mainScreen(composeRule) {
            displaysHeader()
        }
    }

    @Test
    fun testMarketDataIsDisplayed() {
        fakeServer.globalMetricsMarketDataResponse()

        ActivityScenario.launch(MainActivity::class.java)

        mainScreen(composeRule) {
            displaysMarketData()
            displaysMarketCap("$123.00Tr", "-16.08%")
        }
    }

    @Test
    fun testDisplaysPositiveMarketCapVariation() = runTest {
        fakeServer.globalMetricsPositiveBtcDominanceResponse()

        ActivityScenario.launch(MainActivity::class.java)

        mainScreen(composeRule) {
            displaysPositiveMarketCapVariation("10%")
        }
    }
}