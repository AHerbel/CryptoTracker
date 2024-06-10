package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MarketCap(
    value: String,
    variation: String,
    variationIsPositive: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .semantics { contentDescription = "MarketCapContainer" }
            .padding(horizontal = 15.dp)
    ) {
        MarketCapTitle()
        MarketCapValue(value)
        MarketCapVariation(variation, variationIsPositive)
    }
}

@Preview(showBackground = true)
@Composable
fun MarketCapNegativeVariationPreview() {
    MarketCap(
        value = "$1.35Tr",
        variation = "-16.08%",
        variationIsPositive = false
    )
}

@Preview(showBackground = true)
@Composable
fun MarketCapPositiveVariationPreview() {
    MarketCap(
        value = "$1.35Tr",
        variation = "16.08%",
        variationIsPositive = true
    )
}