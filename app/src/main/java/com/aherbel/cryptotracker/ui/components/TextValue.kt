package com.aherbel.cryptotracker.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextValue(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = value,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}