package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherbel.cryptotracker.ui.home.MarketDataUi

@Composable
fun MarketData(marketData: MarketDataUi) {
    Row(
        modifier = Modifier
            .semantics { contentDescription = "MarketData" }
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MarketCap(
            value = marketData.marketCapValue,
            variation = marketData.marketCapVariation,
            variationIsPositive = marketData.marketCapVariationIsPositive
        )
        TwentyFourHsVolume(marketData)
    }
}

@Preview(showBackground = true)
@Composable
fun MarketDataPreview() {
    val data = MarketDataUi(
        marketCapValue = "$1.35Tr",
        marketCapVariation = "-16.08%",
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = "$262.24B"
    )
    MarketData(marketData = data)
}