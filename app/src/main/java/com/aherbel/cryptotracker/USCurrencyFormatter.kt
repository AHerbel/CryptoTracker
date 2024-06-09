package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.CurrencyFormatter
import java.text.DecimalFormat

class USCurrencyFormatter : CurrencyFormatter {
    override fun format(amount: Double): String {
        return DecimalFormat().apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 0
            negativePrefix = "$-"
            positivePrefix = "$"
        }.format(amount)
    }
}
