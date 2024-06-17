package com.aherbel.cryptotracker.application

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class DecimalPercentageFormatterTest {

    private val formatter = DecimalPercentageFormatter()

    private fun testResults(
        expected: String,
        actual: Double
    ) {
        expectThat(formatter.format(actual))
            .describedAs(actual.toString())
            .isEqualTo(expected)
    }

    @Test
    fun `test value get formatted with 2 decimal places`() {
        testResults("10.12%", 10.12)
    }

    @Test
    fun `test value get formatted with 0 decimal places when there are no decimals`() {
        testResults("10%", 10.0)
    }

    @Test
    fun `value get formatted with 1 decimal place when there is only 1 decimal`() {
        testResults("10.1%", 10.1)
    }

    @Test
    fun `value get formatted with a max of 2 decimal places when there is more than 2 decimals`() {
        testResults("10.11%", 10.1122)
    }

    @Test
    fun `value get rounded up when formatted`() {
        testResults("10.12%", 10.1162)
    }

    @Test
    fun `set minPrecisionDigits to 2 formats value with 2 decimal places`() {
        formatter.setMinPrecisionDigits(2)
        testResults("10.12%", 10.1162)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `set minPrecisionDigits throw error when setting negative digits`() {
        formatter.setMinPrecisionDigits(-1)
    }
}