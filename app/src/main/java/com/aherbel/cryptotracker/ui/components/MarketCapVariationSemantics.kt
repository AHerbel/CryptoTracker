package com.aherbel.cryptotracker.ui.components

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val IsVariationPositive = SemanticsPropertyKey<Boolean>("IsVariationPositive")
var SemanticsPropertyReceiver.isVariationPositive by IsVariationPositive