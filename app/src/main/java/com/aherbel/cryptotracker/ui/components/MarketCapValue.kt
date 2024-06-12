package com.aherbel.cryptotracker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MarketCapValue(value: String) {
    TextValue(
        value = value,
        modifier = Modifier.semantics { contentDescription = "MarketCapValue" }
    )
}

@Preview(showBackground = true)
@Composable
fun MarketCapValuePreview() {
    MarketCapValue("$1.35Tr")
}