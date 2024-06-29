package com.aherbel.cryptotracker.builders

import androidx.annotation.StringRes
import com.aherbel.cryptotracker.ui.text.AndroidResourceUiText

data class AndroidResourceUiTextDataBuilder(
    @StringRes private val textRes: Int = 0,
    private val textArgs: List<Any?> = emptyList()
) {

    companion object {
        fun anAndroidResourceUiText() = AndroidResourceUiTextDataBuilder()
    }

    fun withTextRes(@StringRes textRes: Int) = copy(textRes = textRes)

    fun with(vararg textArgs: Any?) = copy(textArgs = textArgs.toList())

    fun with(textArgs: List<Any?>) = copy(textArgs = textArgs)

    fun build(): AndroidResourceUiText = AndroidResourceUiText(textRes, textArgs)
}