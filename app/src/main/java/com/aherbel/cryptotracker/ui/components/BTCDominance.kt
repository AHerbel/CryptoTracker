package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.aherbel.cryptotracker.R

@Composable
fun BTCDominance(btcDominance: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Title(
            title = stringResource(id = R.string.btc_dominance),
            modifier = Modifier.semantics { contentDescription = "BTCDominanceTitle" }
        )
        TextValue(
            value = btcDominance,
            modifier = Modifier.semantics { contentDescription = "BTCDominanceValue" }
        )
    }
}