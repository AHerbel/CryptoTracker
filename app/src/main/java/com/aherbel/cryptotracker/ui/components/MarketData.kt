package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherbel.cryptotracker.ui.home.MarketDataUi
import com.aherbel.cryptotracker.ui.text.PlainUiText
import com.aherbel.cryptotracker.ui.text.resolve

@Composable
fun MarketData(marketData: MarketDataUi) {
    Row(
        modifier = Modifier
            .semantics { contentDescription = "MarketData" }
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val context = LocalContext.current
        MarketCap(
            value = marketData.marketCapValue.resolve(context),
            variation = marketData.marketCapVariation.resolve(context),
            variationIsPositive = marketData.marketCapVariationIsPositive
        )
        TwentyFourHsVolume(
            twentyForHsValue = marketData.twentyFourHsVolume.resolve(context)
        )
        BTCDominance(
            btcDominance = marketData.btcDominance.resolve(context)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MarketDataPreview() {
    val data = MarketDataUi(
        marketCapValue = PlainUiText("$1.35Tr"),
        marketCapVariation = PlainUiText("-16.08%"),
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = PlainUiText("$262.24B"),
        btcDominance = PlainUiText("45.00%")
    )
    MarketData(marketData = data)
}