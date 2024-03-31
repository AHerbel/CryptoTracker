package com.aherbel.cryptotracker.application

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DecimalPercentageFormatterTest {

    private val formatter = DecimalPercentageFormatter()

    @Test
    fun `test value get formatted with 2 decimal places`() {
        assertEquals(
            "Value got formatted with wrong format.",
            "10.12%",
            formatter.format(10.12)
        )
    }

    @Test
    fun `test value get formatted with 0 decimal places when there are no decimals`() {
        assertEquals(
            "Value got formatted with wrong format.",
            "10%",
            formatter.format(10.0)
        )
    }

    @Test
    fun `test value get formatted with 1 decimal place when there is only 1 decimal`() {
        assertEquals(
            "Value got formatted with wrong format.",
            "10.1%",
            formatter.format(10.1)
        )
    }

    @Test
    fun `test value get formatted with a max of 2 decimal places when there is more than 2 decimals`() {
        assertEquals(
            "Value got formatted with wrong format.",
            "10.11%",
            formatter.format(10.1122)
        )
    }

    @Test
    fun `test value get rounded up when formatted`() {
        assertEquals(
            "Value got formatted with wrong format.",
            "10.12%",
            formatter.format(10.1162)
        )
    }
}