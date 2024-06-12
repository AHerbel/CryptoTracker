package com.aherbel.cryptotracker.application

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DecimalPercentageFormatterTest {

    private val formatter = DecimalPercentageFormatter()

    private fun testResults(
        expected: String,
        actual: String
    ) {
        assertEquals(
            "Value got formatted with wrong format. Expected: $expected, got: $actual",
            expected,
            actual
        )
    }

    @Test
    fun `test value get formatted with 2 decimal places`() {
        testResults("10.12%", formatter.format(10.12))
    }

    @Test
    fun `test value get formatted with 0 decimal places when there are no decimals`() {
        testResults("10%", formatter.format(10.0))
    }

    @Test
    fun `test value get formatted with 1 decimal place when there is only 1 decimal`() {
        testResults("10.1%", formatter.format(10.1))
    }

    @Test
    fun `test value get formatted with a max of 2 decimal places when there is more than 2 decimals`() {
        testResults("10.11%", formatter.format(10.1122))
    }

    @Test
    fun `test value get rounded up when formatted`() {
        testResults("10.12%", formatter.format(10.1162))
    }

    @Test
    fun setMinPrecisionDigits_afterFormatting_formatsValueRespectingMinimumFractionDigits() {
        formatter.setMinPrecisionDigits(2)
        testResults("10.12%", formatter.format(10.1162))
    }

    @Test(expected = IllegalArgumentException::class)
    fun setMinPrecisionDigits_whenConfigured_throwErrorIfDigitsAreNegative() {
        formatter.setMinPrecisionDigits(-1)
    }
}