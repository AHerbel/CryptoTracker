package com.aherbel.cryptotracker

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import com.aherbel.cryptotracker.application.di.UrlModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@UninstallModules(UrlModule::class)
class MainActivityTest {

    private val mockWebServer = MockWebServer()

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testHeaderIsDisplayed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("""
            {
                "data": {
                     "btc_dominance": 100000,
                     "btc_dominance_24h_percentage_change": -16.08,
                     "quote": {
                         "USD": {
                             "total_market_cap": 123,
                             "total_volume_24h": 123
                         }
                     }
                },
                "status": {
                    "timestamp": "123",
                    "error_code": 1234,
                    "error_message": "asd",
                    "elapsed": 1234,
                    "credit_count": 123,
                    "notice": "asdas"
                }
            }
        """.trimIndent()))
        ActivityScenario.launch(MainActivity::class.java)
        mainScreen(composeRule) {
            displaysHeader()
        }
    }

    @Test
    fun testMarketDataIsDisplayed() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("""
            {
                "data": {
                     "btc_dominance": 100000,
                     "btc_dominance_24h_percentage_change": -16.08,
                     "quote": {
                         "USD": {
                             "total_market_cap": 123,
                             "total_volume_24h": 123
                         }
                     }
                },
                "status": {
                    "timestamp": "123",
                    "error_code": 1234,
                    "error_message": "asd",
                    "elapsed": 1234,
                    "credit_count": 123,
                    "notice": "asdas"
                }
            }
        """.trimIndent()))
        ActivityScenario.launch(MainActivity::class.java)
        mainScreen(composeRule) {
            displaysMarketData()
            displaysMarketCap("$123.00Tr", "-16.08%")
        }
    }

    @Test
    fun testDisplaysPositiveMarketCapVariation() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("""
            {
                "data": {
                     "btc_dominance": 100000,
                     "btc_dominance_24h_percentage_change": 10,
                     "quote": {
                         "USD": {
                             "total_market_cap": 123,
                             "total_volume_24h": 123
                         }
                     }
                },
                "status": {
                    "timestamp": "123",
                    "error_code": 1234,
                    "error_message": "asd",
                    "elapsed": 1234,
                    "credit_count": 123,
                    "notice": "asdas"
                }
            }
        """.trimIndent()))

        ActivityScenario.launch(MainActivity::class.java)

        mainScreen(composeRule) {
            displaysPositiveMarketCapVariation("10%")
        }
    }
}