package com.aherbel.cryptotracker.infra

import androidx.test.core.app.ActivityScenario
import com.aherbel.cryptotracker.HomeActivity

class FakeApplication {

    fun launchHomeScreen() {
        ActivityScenario.launch(HomeActivity::class.java)
    }
}