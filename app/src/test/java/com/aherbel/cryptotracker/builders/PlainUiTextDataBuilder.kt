package com.aherbel.cryptotracker.builders

import com.aherbel.cryptotracker.ui.text.PlainUiText

data class PlainUiTextDataBuilder(
    private var text: String
) {

    companion object {
        fun aPlainUiText(): PlainUiTextDataBuilder = PlainUiTextDataBuilder("")
    }

    fun withText(text: String): PlainUiTextDataBuilder = this.copy(text = text)

    fun build(): PlainUiText = PlainUiText(text)
}