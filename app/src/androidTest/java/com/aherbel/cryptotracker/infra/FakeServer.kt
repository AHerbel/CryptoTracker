package com.aherbel.cryptotracker.infra

import com.aherbel.cryptotracker.application.network.AddApiKeyHeaderInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
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

    private fun readJsonFromResources(file: String, configure: (JSONObject) -> Unit = {}): String {
        val rawResponse = javaClass.classLoader?.getResourceAsStream(file)?.reader().use { it?.readText() }
        val jsonResponse = JSONObject(rawResponse.orEmpty())
        configure(jsonResponse)
        return jsonResponse.toString()
    }

    fun willAnswerDefaultMarketDataInformation() {
        val response = readJsonFromResources("global_metrics_default_response.json")
        configure200Response(response)
    }

    fun willAnswer24HsVolume(twentyFourHsVolume: Double) {
        val response = readJsonFromResources("global_metrics_default_response.json") { jsonResponse ->
            jsonResponse
                .getJSONObject("data")
                .getJSONObject("quote")
                .getJSONObject("USD")
                .put("total_volume_24h", twentyFourHsVolume)
        }
        configure200Response(response)
    }

    fun willAnswerMarketDataWithMarketCapValueOf(marketCapValue: Double) {
        val response = readJsonFromResources("global_metrics_default_response.json") { jsonResponse ->
            jsonResponse
                .getJSONObject("data")
                .getJSONObject("quote")
                .getJSONObject("USD")
                .put("total_market_cap", marketCapValue)
        }
        configure200Response(response)
    }

    fun willAnswerMarketDataWithMarketCapPercentageChange(marketCapPercentageChange: Int) {
        val response = readJsonFromResources("global_metrics_default_response.json") { jsonResponse ->
            jsonResponse
                .getJSONObject("data")
                .getJSONObject("quote")
                .getJSONObject("USD")
                .put("total_market_cap_yesterday_percentage_change", marketCapPercentageChange)
        }
        configure200Response(response)
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

    private fun configure200Response(response: String) {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(response)
        )
    }

    fun willAnswerBTCDominance(btcDominance: Int) {
        val response = readJsonFromResources("global_metrics_default_response.json") { jsonResponse ->
            jsonResponse
                .getJSONObject("data")
                .put("btc_dominance", btcDominance)
        }
        configure200Response(response)
    }

    fun willAnswerError400OnMarketData() {
        configure400Response()
    }

    private fun configure400Response() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        )
    }

}
