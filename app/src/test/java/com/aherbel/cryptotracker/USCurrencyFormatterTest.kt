package com.aherbel.cryptotracker

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import junitparams.naming.TestCaseName
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class USCurrencyFormatterTest {

    @Test
    @Parameters(method = "formatAmountTestData")
    @TestCaseName(value = "test format amount {0} returns {1}")
    fun formatAmount_givenAFloatingPointNumber_addDollarSignAtFront(amount: Double, expected: String) {
        assertEquals(
            "Value is not properly formatted",
            expected,
            USCurrencyFormatter().format(amount)
        )
    }

    fun formatAmountTestData(): Array<Any> = arrayOf(
        arrayOf(-1000000.01, "-$1,000,000.01"),
        arrayOf(-100000.01, "-$100,000.01"),
        arrayOf(-10000.01, "-$10,000.01"),
        arrayOf(-1000.01, "-$1,000.01"),
        arrayOf(-100.01, "-$100.01"),
        arrayOf(-10.01, "-$10.01"),
        arrayOf(-1.01, "-$1.01"),
        arrayOf(-0.01, "-$0.01"),
        arrayOf(-1000000.00, "-$1,000,000"),
        arrayOf(-100000.00, "-$100,000"),
        arrayOf(-10000.00, "-$10,000"),
        arrayOf(-1000.00, "-$1,000"),
        arrayOf(-100.00, "-$100"),
        arrayOf(-10.00, "-$10"),
        arrayOf(-1.00, "-$1"),
        arrayOf(-0.00, "-$0"),
        arrayOf(1000000.01, "$1,000,000.01"),
        arrayOf(100000.01, "$100,000.01"),
        arrayOf(10000.01, "$10,000.01"),
        arrayOf(1000.01, "$1,000.01"),
        arrayOf(100.01, "$100.01"),
        arrayOf(10.01, "$10.01"),
        arrayOf(1.01, "$1.01"),
        arrayOf(0.01, "$0.01"),
        arrayOf(1000000.0, "$1,000,000"),
        arrayOf(100000.0, "$100,000"),
        arrayOf(10000.0, "$10,000"),
        arrayOf(1000.0, "$1,000"),
        arrayOf(100.0, "$100"),
        arrayOf(10.0, "$10"),
        arrayOf(1.0, "$1"),
        arrayOf(0.0, "$0")
    )
}