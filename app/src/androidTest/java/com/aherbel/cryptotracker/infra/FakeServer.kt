package com.aherbel.cryptotracker.infra

import com.aherbel.cryptotracker.application.network.AddApiKeyHeaderInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import java.net.HttpURLConnection

class FakeServer {

    private val mockWebServer = MockWebServer()

    fun start() {
        mockWebServer.requireClientAuth()
        mockWebServer.start(8080)
    }

    fun shutdown() {
        mockWebServer.shutdown()
    }

    private fun readJsonFromResources(file: String): String? {
        return javaClass.classLoader?.getResourceAsStream(file)?.reader().use { it?.readText() }
    }

    fun willAnswerDefaultMarketDataInformation() {
        val response = readJsonFromResources("global_metrics_default_response.json")
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(response)
        )
    }

    fun willAnswerMarketDataInformation() {
        val response = readJsonFromResources("global_metrics_market_data_response.json")
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(response)
        )
    }

    fun willAnswerMarketDataWithPositiveMarketCapPercentageChange() {
        val response = readJsonFromResources("global_metrics_positive_total_market_cap_percentage_change_response.json")
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(response)
        )
    }

    fun requestShouldContainsNonEmptyApiKeyHeader() {
        val request = mockWebServer.takeRequest()
        val apiKeyHeader = request.getHeader(AddApiKeyHeaderInterceptor.HEADER_API_KEY)
        assertNotNull(
            "Api Key header not present. Header=${AddApiKeyHeaderInterceptor.HEADER_API_KEY}",
            apiKeyHeader
        )
        assertFalse(
            "Api Key is empty.",
            apiKeyHeader.isEmpty()
        )
    }

    fun willAnswerMarketDataWithNegativeMarketCapPercentageChange() {
        val response = readJsonFromResources("global_metrics_negative_total_market_cap_percentage_change_response.json")
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(response)
        )
    }

}
