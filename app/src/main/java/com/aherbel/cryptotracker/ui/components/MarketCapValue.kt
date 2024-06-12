package com.aherbel.cryptotracker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MarketCapValue(value: String) {
    TextValue(value)
}

@Preview(showBackground = true)
@Composable
fun MarketCapValuePreview() {
    MarketCapValue("$1.35Tr")
}