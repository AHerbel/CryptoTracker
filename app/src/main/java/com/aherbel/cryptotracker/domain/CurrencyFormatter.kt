package com.aherbel.cryptotracker.domain

interface CurrencyFormatter {

    fun format(amount: Double): String
}