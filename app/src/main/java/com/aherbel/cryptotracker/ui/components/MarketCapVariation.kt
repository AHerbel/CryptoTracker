package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarketCapVariation(
    variation: String,
    isPositive: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val variationColor = if (isPositive) Color.Green else Color.Red
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            tint = variationColor,
            contentDescription = "MarketCapVariationIndicator",
            modifier = Modifier
                .size(20.dp)
                .rotate(if (isPositive) 180f else 0f)
                .semantics { isVariationPositive = isPositive }
        )
        Text(
            text = variation,
            color = variationColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.semantics { contentDescription = "MarketCapVariation" }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NegativeMarketCapVariationPreview() {
    MarketCapVariation("-16.08%", false)
}

@Preview(showBackground = true)
@Composable
fun PositiveMarketCapVariationPreview() {
    MarketCapVariation("16.08%", true)
}