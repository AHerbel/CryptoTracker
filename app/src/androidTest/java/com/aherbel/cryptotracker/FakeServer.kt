package com.aherbel.cryptotracker

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class FakeServer {

    private val mockWebServer = MockWebServer()

    fun start() {
        mockWebServer.start(8080)
    }

    fun shutdown() {
        mockWebServer.shutdown()
    }

    private fun readJsonFromResources(file: String): String? {
        return javaClass.classLoader?.getResourceAsStream(file)?.reader().use { it?.readText() }
    }

    fun globalMetricsDefaultResponse() {
        val response = readJsonFromResources("global_metrics_default_response.json")
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
    }

    fun globalMetricsMarketDataResponse() {
        val response = readJsonFromResources("global_metrics_market_data_response.json")
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
    }

    fun globalMetricsPositiveBtcDominanceResponse() {
        val response = readJsonFromResources("global_metrics_positive_btc_dominance_response.json")
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(response))
    }

}
