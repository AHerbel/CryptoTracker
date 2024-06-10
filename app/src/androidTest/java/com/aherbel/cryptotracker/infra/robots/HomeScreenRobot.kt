package com.aherbel.cryptotracker.infra.robots

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.aherbel.cryptotracker.matchers.assertTextColor

fun homeScreen(
    composeTestRule: ComposeTestRule,
    block: HomeScreenRobot.() -> Unit
) = HomeScreenRobot(composeTestRule).apply(block)

class HomeScreenRobot(composeTestRule: ComposeTestRule) {

    private val livePricesText = composeTestRule.onNodeWithContentDescription("LivePrices")
    private val marketCapValue = composeTestRule.onNodeWithContentDescription("MarketCapValue")
    private val marketCapVariation = composeTestRule.onNodeWithContentDescription("MarketCapVariation")
    private val marketCapVariationArrow = composeTestRule.onNodeWithContentDescription("MarketCapVariationArrow")
    private val twentyFourHsVolumeText = composeTestRule.onNodeWithContentDescription("24HsVolumeValue")

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

}