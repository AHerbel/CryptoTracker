package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MarketCap(
    value: String,
    variation: String,
    variationColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .semantics { contentDescription = "MarketCapContainer" }
            .padding(horizontal = 15.dp)
    ) {
        MarketCapTitle()
        MarketCapValue(value)
        MarketCapVariation(variation, variationColor)
    }
}

@Preview(showBackground = true)
@Composable
fun MarketCapPreview() {
    MarketCap(
        value = "$1.35Tr",
        variation = "-16.08%",
        variationColor = Color.Red
    )
}