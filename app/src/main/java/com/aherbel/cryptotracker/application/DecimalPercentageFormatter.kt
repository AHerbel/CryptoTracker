package com.aherbel.cryptotracker.application

import com.aherbel.cryptotracker.domain.PercentageFormatter
import java.text.DecimalFormat

class DecimalPercentageFormatter : PercentageFormatter {

    private val decimalFormatter = DecimalFormat().apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 0
        positiveSuffix = "%"
        negativeSuffix = "%"
    }
    override fun format(value: Double): String = decimalFormatter.format(value)
}