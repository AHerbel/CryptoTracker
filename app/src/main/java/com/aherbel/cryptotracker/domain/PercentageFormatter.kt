package com.aherbel.cryptotracker.domain

interface PercentageFormatter {
    fun setMinPrecisionDigits(digits: Int)
    fun format(value: Double): String
}