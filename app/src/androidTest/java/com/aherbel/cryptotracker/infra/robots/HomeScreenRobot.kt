package com.aherbel.cryptotracker.infra.robots

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.test.core.app.ApplicationProvider
import com.aherbel.cryptotracker.R
import com.aherbel.cryptotracker.matchers.assertTextColor
import com.aherbel.cryptotracker.ui.components.IsVariationPositive
import com.aherbel.cryptotracker.ui.home.IsRefreshing

fun homeScreen(
    composeTestRule: ComposeTestRule,
    block: HomeScreenRobot.() -> Unit
) = HomeScreenRobot(composeTestRule).apply(block)

class HomeScreenRobot(private val composeTestRule: ComposeTestRule) {

    private val livePricesText = composeTestRule.onNodeWithContentDescription("LivePrices")
    private val marketCapTitle = composeTestRule.onNodeWithContentDescription("MarketCapTitle")
    private val marketCapValue = composeTestRule.onNodeWithContentDescription("MarketCapValue")
    private val marketCapVariation = composeTestRule.onNodeWithContentDescription("MarketCapVariation")
    private val marketCapVariationArrow = composeTestRule.onNodeWithContentDescription("MarketCapVariationIndicator")
    private val twentyFourHsVolumeTitle = composeTestRule.onNodeWithContentDescription("24HsVolumeTitle")
    private val twentyFourHsVolumeText = composeTestRule.onNodeWithContentDescription("24HsVolumeValue")
    private val btcDominanceTitle = composeTestRule.onNodeWithContentDescription("BTCDominanceTitle")
    private val btcDominanceValue = composeTestRule.onNodeWithContentDescription("BTCDominanceValue")
    private val homeContainer = composeTestRule.onNodeWithContentDescription("HomeContainer")

    fun displaysPositiveMarketCapVariationOf(variation: String) {
        displaysMarketCapVariationOf(variation, Color.Green, true)
    }

    fun displaysNegativeMarketCapVariationOf(variation: String) {
        displaysMarketCapVariationOf(variation, Color.Red, false)
    }

    private fun displaysMarketCapVariationOf(variation: String, color: Color, isVariationPositive: Boolean) {
        marketCapVariation
            .assertIsDisplayed()
            .assertTextEquals(variation)
            .assertTextColor(color)
        composeTestRule
            .onNode(SemanticsMatcher.expectValue(IsVariationPositive, isVariationPositive))
            .assertIsDisplayed()
    }

    fun displaysTitle(title: String) {
        livePricesText
            .assertIsDisplayed()
            .assertTextEquals(title)
    }

    fun displaysMarketCapValueOf(value: String) {
        marketCapValue
            .assertIsDisplayed()
            .assertTextEquals(value)
    }

    fun displays24HsVolumeOf(twentyFourHsVolume: String) {
        twentyFourHsVolumeText
            .assertIsDisplayed()
            .assertTextEquals(twentyFourHsVolume)
    }

    fun displaysBTCDominanceOf(btcDominance: String) {
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

    fun doesNotDisplayMarketCapVariation() {
        marketCapVariation.assertDoesNotExist()
        marketCapVariationArrow.assertDoesNotExist()
    }

    fun displaysNotAvailable24HsVolume() {
        twentyFourHsVolumeTitle.assertIsDisplayed()
        twentyFourHsVolumeText
            .assertIsDisplayed()
            .assertTextEquals(notAvailable())
    }

    fun performPullToRefresh() {
        homeContainer.performTouchInput { swipeDown() }
    }

    fun displaysLoading() {
        composeTestRule
            .onNode(SemanticsMatcher.expectValue(IsRefreshing, true))
            .assertExists()
    }

    fun hidesLoading() {
        composeTestRule
            .onNode(SemanticsMatcher.expectValue(IsRefreshing, false))
            .assertExists()
    }
}