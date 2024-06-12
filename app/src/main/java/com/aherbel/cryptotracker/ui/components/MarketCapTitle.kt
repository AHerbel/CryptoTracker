package com.aherbel.cryptotracker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.aherbel.cryptotracker.R

@Composable
fun MarketCapTitle() {
    Title(
        title = stringResource(id = R.string.market_cap),
        modifier = Modifier.semantics { contentDescription = "MarketCapTitle" }
    )
}

@Preview(showBackground = true)
@Composable
fun MarketCapTitlePreview() {
    MarketCapTitle()
}