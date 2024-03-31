package com.aherbel.cryptotracker.domain

interface PercentageFormatter {

    fun format(value: Double): String
}