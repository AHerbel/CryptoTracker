package com.aherbel.cryptotracker.application.network

import com.google.gson.annotations.SerializedName

data class Response<DATA>(
    val data: DATA,
    val status: Status
)

data class Status(
    val timestamp: String,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: String?,
    val elapsed: Int,
    @SerializedName("credit_count")
    val creditCount: Int,
    val notice: String?
)
