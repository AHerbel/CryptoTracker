package com.aherbel.cryptotracker.ui.text

import android.content.Context

fun UiText.resolve(context: Context): String = when (this) {
    is PlainUiText -> text
    is AndroidResourceUiText -> context.getString(textRes, *args.toTypedArray())
    else -> ""
}