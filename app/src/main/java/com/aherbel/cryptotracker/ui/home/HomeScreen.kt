package com.aherbel.cryptotracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.aherbel.cryptotracker.ui.components.MarketData
import com.aherbel.cryptotracker.ui.components.TopBar
import com.aherbel.cryptotracker.ui.text.PlainUiText
import com.aherbel.cryptotracker.ui.theme.CryptoTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    marketData: MarketDataUi,
    isLoading: Boolean,
    onRefresh: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = Modifier.semantics {
                contentDescription = "HomeContainer"
                isRefreshing = isLoading
            }
        ) {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                TopBar()
                MarketData(marketData = marketData)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPositiveMarketCapVariationPreview() {
    val data = MarketDataUi(
        marketCapValue = PlainUiText("$1.35Tr"),
        marketCapVariation = PlainUiText("16.08%"),
        marketCapVariationIsPositive = true,
        twentyFourHsVolume = PlainUiText("1.35Tr"),
        btcDominance = PlainUiText("16.08%")
    )
    CryptoTrackerTheme {
        HomeScreen(marketData = data, isLoading = false, onRefresh = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenNegativeMarketCapVariationPreview() {
    val data = MarketDataUi(
        marketCapValue = PlainUiText("$1.35Tr"),
        marketCapVariation = PlainUiText("-16.08%"),
        marketCapVariationIsPositive = false,
        twentyFourHsVolume = PlainUiText("1.35Tr"),
        btcDominance = PlainUiText("-16.08%")
    )
    CryptoTrackerTheme {
        HomeScreen(marketData = data, isLoading = false, onRefresh = {})
    }
}