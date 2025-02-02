package com.aherbel.cryptotracker.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherbel.cryptotracker.R

@Composable
fun TwentyFourHsVolume(twentyForHsValue: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Title(
            title = stringResource(id = R.string.twenty_four_hs_volume),
            modifier = Modifier.semantics { contentDescription = "24HsVolumeTitle" }
        )
        TextValue(
            value = twentyForHsValue,
            modifier = Modifier.semantics { contentDescription = "24HsVolumeValue" }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TwentyForHsVolumePreview() {
    TwentyFourHsVolume("$252.53M")
}