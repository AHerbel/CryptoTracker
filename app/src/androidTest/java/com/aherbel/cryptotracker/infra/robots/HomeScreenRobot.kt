package com.aherbel.cryptotracker.infra.robots

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import com.aherbel.cryptotracker.R
import com.aherbel.cryptotracker.matchers.assertTextColor

fun homeScreen(
    composeTestRule: ComposeTestRule,
    block: HomeScreenRobot.() -> Unit
) = HomeScreenRobot(composeTestRule).apply(block)

class HomeScreenRobot(composeTestRule: ComposeTestRule) {

    private val livePricesText = composeTestRule.onNodeWithContentDescription("LivePrices")
    private val marketCapTitle = composeTestRule.onNodeWithContentDescription("MarketCapTitle")
    private val marketCapValue = composeTestRule.onNodeWithContentDescription("MarketCapValue")
    private val marketCapVariation = composeTestRule.onNodeWithContentDescription("MarketCapVariation")
    private val marketCapVariationArrow = composeTestRule.onNodeWithContentDescription("MarketCapVariationArrow")
    private val twentyFourHsVolumeText = composeTestRule.onNodeWithContentDescription("24HsVolumeValue")
    private val btcDominanceTitle = composeTestRule.onNodeWithContentDescription("BTCDominanceTitle")
    private val btcDominanceValue = composeTestRule.onNodeWithContentDescription("BTCDominanceValue")

    fun displaysPositiveMarketCapVariation(variation: String) {
        marketCapVariation
            .assertIsDisplayed()
            .assertTextEquals(variation)
            .assertTextColor(Color.Green)
        marketCapVariationArrow
            .assertIsDisplayed()
    }

    fun displaysNegativeMarketCapVariation(variation: String) {
        marketCapVariation
            .assertIsDisplayed()
            .assertTextEquals(variation)
            .assertTextColor(Color.Red)
        marketCapVariationArrow
            .assertIsDisplayed()
    }

    fun displaysTitle(title: String) {
        livePricesText
            .assertIsDisplayed()
            .assertTextEquals(title)
    }

    fun displaysMarketCapValue(value: String) {
        marketCapValue
            .assertIsDisplayed()
            .assertTextEquals(value)
    }

    fun displays24HsVolume(twentyFourHsVolume: String) {
        twentyFourHsVolumeText
            .assertIsDisplayed()
            .assertTextEquals(twentyFourHsVolume)
    }

    fun displaysBTCDominance(btcDominance: String) {
        btcDominanceTitle.assertIsDisplayed()
        btcDominanceValue
            .assertIsDisplayed()
            .assertTextEquals(btcDominance)
    }

    fun displaysNotAvailableBtcDominance() {
        btcDominanceTitle.assertIsDisplayed()
        btcDominanceValue
            .assertIsDisplayed()
            .assertTextEquals(notAvailable())
    }

    private fun notAvailable(): String {
        val notAvailable = ApplicationProvider
            .getApplicationContext<Context>()
            .getString(R.string.not_available)
        return notAvailable
    }

    fun displaysNotAvailableMarketCap() {
        marketCapTitle.assertIsDisplayed()
        marketCapValue
            .assertIsDisplayed()
            .assertTextEquals(notAvailable())
    }
}