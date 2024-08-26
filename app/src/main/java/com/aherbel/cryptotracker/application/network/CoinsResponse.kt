package com.aherbel.cryptotracker.application.network

data class CoinsResponse(
    val data: List<Coin>
)

data class Coin(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: Quote
)
