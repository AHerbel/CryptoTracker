package com.aherbel.cryptotracker

import androidx.test.core.app.ActivityScenario
import com.aherbel.cryptotracker.application.di.NetworkConfigurationModule
import com.aherbel.cryptotracker.infra.FakeServer
import com.aherbel.cryptotracker.infra.FakeServerRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(NetworkConfigurationModule::class)
class NetworkEndToEndTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val fakeServerRule = FakeServerRule()

    private val fakeServer: FakeServer get() = fakeServerRule.fakeServer

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testNetworkRequestIncludeNonEmptyApiKeyHeader() {
        fakeServer.willAnswerDefaultMarketDataInformation()

        ActivityScenario.launch(HomeActivity::class.java)

        fakeServer.requestShouldContainsNonEmptyApiKeyHeader()
    }
}