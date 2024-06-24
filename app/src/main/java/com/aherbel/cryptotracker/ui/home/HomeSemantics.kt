package com.aherbel.cryptotracker.ui.home

import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val IsRefreshing = SemanticsPropertyKey<Boolean>("IsRefreshing")
var SemanticsPropertyReceiver.isRefreshing by IsRefreshing