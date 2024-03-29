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

    fun globalMetricsSuccess(btcDominance: Double = -16.08) {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody("""
            {
                "data": {
                     "btc_dominance": 100000,
                     "btc_dominance_24h_percentage_change": ${btcDominance},
                     "quote": {
                         "USD": {
                             "total_market_cap": 123,
                             "total_volume_24h": 123
                         }
                     }
                },
                "status": {
                    "timestamp": "123",
                    "error_code": 1234,
                    "error_message": "asd",
                    "elapsed": 1234,
                    "credit_count": 123,
                    "notice": "asdas"
                }
            }
        """.trimIndent()))
    }

}
