package com.aherbel.cryptotracker.ui.text

import androidx.annotation.StringRes

data class AndroidResourceUiText(
    @StringRes val textRes: Int,
    val args: List<Any?> = emptyList()
) : UiText