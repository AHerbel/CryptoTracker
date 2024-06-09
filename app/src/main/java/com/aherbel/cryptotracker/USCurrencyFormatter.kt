package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.CurrencyFormatter
import java.text.NumberFormat
import java.util.Locale

class USCurrencyFormatter : CurrencyFormatter {
    override fun format(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale.US).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 0
        }.format(amount)
    }
}
