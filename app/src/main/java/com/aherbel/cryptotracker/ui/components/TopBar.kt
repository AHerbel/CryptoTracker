package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aherbel.cryptotracker.R

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .semantics { contentDescription = "Header" }
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        TopBarButton(
            modifier = Modifier
                .semantics { contentDescription = "Info" }
                .wrapContentWidth(Alignment.Start)
        ) {
            Text(text = "i", fontSize = 15.sp)
        }
        Text(
            text = stringResource(id = R.string.live_prices),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .semantics { contentDescription = "LivePrices" }
                .padding(horizontal = 10.dp)
        )
        TopBarButton(modifier = Modifier.wrapContentWidth(Alignment.End)) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Portfolio"
            )
        }
    }
}