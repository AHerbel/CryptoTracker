package com.aherbel.cryptotracker

import com.aherbel.cryptotracker.domain.CurrencyFormatter
import com.ibm.icu.number.Notation
import com.ibm.icu.number.NumberFormatter
import com.ibm.icu.number.Precision
import com.ibm.icu.util.Currency
import java.util.Locale

class USCurrencyFormatter : CurrencyFormatter {
    override fun format(amount: Double): String {
        return NumberFormatter
            .with()
            .locale(Locale.US)
            .unit(Currency.getInstance(Locale.US))
            .precision(Precision.currency(Currency.CurrencyUsage.STANDARD))
            .notation(Notation.compactShort())
            .format(amount)
            .toString()
    }
}
