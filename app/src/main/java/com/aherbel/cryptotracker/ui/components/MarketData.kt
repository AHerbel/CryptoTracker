package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.aherbel.cryptotracker.ui.main.MarketDataUi

@Composable
fun MarketData(marketData: MarketDataUi) {
    Row(modifier = Modifier.semantics { contentDescription = "MarketData" }) {
        MarketCap(
            value = marketData.marketCapValue,
            variation = marketData.marketCapVariation,
            variationColor = marketData.marketCapVariationColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MarketDataPreview() {
    val data = MarketDataUi(
        marketCapValue = "$1.35Tr",
        marketCapVariation = "-16.08%",
        marketCapVariationColor = Color.Red
    )
    MarketData(marketData = data)
}