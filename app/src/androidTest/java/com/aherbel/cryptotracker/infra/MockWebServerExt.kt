package com.aherbel.cryptotracker.infra

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection

fun MockWebServer.return200With(body: String) {
    enqueue(
        MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(body)
    )
}

fun MockWebServer.return400() {
    enqueue(
        MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
    )
}