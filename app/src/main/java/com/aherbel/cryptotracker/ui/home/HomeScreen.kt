package com.aherbel.cryptotracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aherbel.cryptotracker.ui.components.MarketData
import com.aherbel.cryptotracker.ui.components.TopBar
import com.aherbel.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun HomeScreen(marketData: MarketDataUi) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
        ) {
            TopBar()
            MarketData(marketData = marketData)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPositiveMarketCapVariationPreview() {
    val data = MarketDataUi(
        marketCapValue = "$1.35Tr",
        marketCapVariation = "16.08%",
        marketCapVariationIsPositive = true,
        twentyFourHsVolume = "1.35Tr"
    )
    CryptoTrackerTheme {
        HomeScreen(marketData = data)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenNegativeMarketCapVariationPreview() {
    val data = MarketDataUi(
        marketCapValue = "$1.35Tr",
        marketCapVariation = "-16.08%",
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = "1.35Tr"
    )
    CryptoTrackerTheme {
        HomeScreen(marketData = data)
    }
}