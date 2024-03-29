package com.aherbel.cryptotracker.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.aherbel.cryptotracker.R

@Composable
fun MarketCapTitle() {
    Text(
        text = stringResource(id = R.string.market_cap),
        color = Color.Black,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.semantics { contentDescription = "MarketCapTitle" }
    )
}

@Preview(showBackground = true)
@Composable
fun MarketCapTitlePreview() {
    MarketCapTitle()
}