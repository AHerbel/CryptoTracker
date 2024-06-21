package com.aherbel.cryptotracker.ui.text

import androidx.test.core.app.ApplicationProvider
import com.aherbel.cryptotracker.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.ParameterizedRobolectricTestRunner.Parameters
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@RunWith(ParameterizedRobolectricTestRunner::class)
class TextKtTest(
    private val uiText: UiText,
    private val expectedText: String
) {

    companion object {
        @JvmStatic
        @Parameters(name = "{0} value should be: {1}")
        fun data() = listOf(
            arrayOf(PlainUiText("test"), "test"),
            arrayOf(AndroidResourceUiText(R.string.not_available), "N/A")
        )
    }

    @Test
    fun `resolve UiText value computes correct value`() {
        val resolvedText = uiText.resolve(ApplicationProvider.getApplicationContext())
        expectThat(resolvedText)
            .describedAs("resolved UiText value")
            .isEqualTo(expectedText)
    }
}