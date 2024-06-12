package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aherbel.cryptotracker.R

@Composable
fun TwentyFourHsVolume(twentyForHsValue: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .semantics { contentDescription = "24HsVolumeContainer" }
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.twenty_four_hs_volume),
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.semantics { contentDescription = "24HsVolume" }
        )
        Text(
            text = twentyForHsValue,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.semantics { contentDescription = "24HsVolumeValue" }
        )
    }
}