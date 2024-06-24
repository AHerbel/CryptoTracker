package com.aherbel.cryptotracker.infra

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

fun MockWebServer.return200With(
    body: String,
    bodyDelay: Long = 0,
    bodyDelayUnit: TimeUnit = TimeUnit.MILLISECONDS
) {
    enqueue(
        MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(body)
            .setBodyDelay(bodyDelay, bodyDelayUnit)
    )
}

fun MockWebServer.return400() {
    enqueue(
        MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
    )
}