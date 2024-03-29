package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBarButton(
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = {},
        contentPadding = PaddingValues(horizontal = 10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 15.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 15.dp,
            focusedElevation = 10.dp
        ),
        modifier = modifier
            .wrapContentWidth(Alignment.End)
            .size(50.dp),
        content = content
    )
}