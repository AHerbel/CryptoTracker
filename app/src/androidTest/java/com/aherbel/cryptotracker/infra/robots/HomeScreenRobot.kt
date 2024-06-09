package com.aherbel.cryptotracker.infra.robots

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
import com.aherbel.cryptotracker.matchers.assertTextColor

fun homeScreen(
    composeTestRule: ComposeTestRule,
    block: HomeScreenRobot.() -> Unit
) = HomeScreenRobot(composeTestRule).apply(block)

class HomeScreenRobot(composeTestRule: ComposeTestRule) {

    private val infoButton = composeTestRule.onNodeWithContentDescription("Info")
    private val livePricesText = composeTestRule.onNodeWithContentDescription("LivePrices")
    private val portfolioButton = composeTestRule.onNodeWithContentDescription("Portfolio")
    private val headerRow = composeTestRule.onNodeWithContentDescription("Header")
    private val marketData = composeTestRule.onNodeWithContentDescription("MarketData")
    private val marketCapContainer = composeTestRule.onNodeWithContentDescription("MarketCapContainer")
    private val marketCapTitle = composeTestRule.onNodeWithContentDescription("MarketCapTitle")
    private val marketCapValue = composeTestRule.onNodeWithContentDescription("MarketCapValue")
    private val marketCapVariation = composeTestRule.onNodeWithContentDescription("MarketCapVariation")
    private val marketCapVariationArrow = composeTestRule.onNodeWithContentDescription("MarketCapVariationArrow")

    private fun displaysInfo() {
        infoButton
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun displaysLivePrices() {
        livePricesText
            .assertIsDisplayed()
            .assertTextEquals("Live Prices")
    }

    private fun displaysPortfolio() {
        portfolioButton
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    private fun displaysHeaderRowInLeftTopCorner() {
        headerRow
            .assertExists()
            .assertPositionInRootIsEqualTo(0.dp, 0.dp)
    }

    fun displaysHeader() {
        displaysLivePrices()
        displaysInfo()
        displaysPortfolio()
        displaysHeaderRowInLeftTopCorner()
    }

    fun displaysMarketData() {
        marketData.assertExists()
    }

    fun displaysMarketCap(
        value: String,
        variation: String
    ) {
        marketCapContainer.assertExists()
        marketCapTitle
            .assertIsDisplayed()
            .assertTextEquals("Market Cap")
        marketCapValue
            .assertIsDisplayed()
            .assertTextEquals(value)
        marketCapVariation
            .assertIsDisplayed()
            .assertTextEquals(variation)
        marketCapVariationArrow
            .assertIsDisplayed()
    }

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

}