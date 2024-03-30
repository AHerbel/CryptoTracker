package com.aherbel.cryptotracker.infra

import org.junit.rules.TestWatcher
import org.junit.runner.Description

class FakeServerRule : TestWatcher() {

    val fakeServer = FakeServer()

    override fun starting(description: Description?) {
        fakeServer.start()
    }

    override fun finished(description: Description?) {
        fakeServer.shutdown()
    }

}