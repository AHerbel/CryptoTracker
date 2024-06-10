package com.aherbel.cryptotracker.domain

data class MarketData(
    val marketCapValue: Double,
    val marketCapVariation: Double,
    val twentyFourHourVolume: Double,
)