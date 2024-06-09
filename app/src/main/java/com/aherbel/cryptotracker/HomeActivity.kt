package com.aherbel.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aherbel.cryptotracker.ui.main.MainScreen
import com.aherbel.cryptotracker.ui.main.MainViewModel
import com.aherbel.cryptotracker.ui.theme.CryptoTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestMarketData()

        setContent {
            CryptoTrackerTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                MainScreen(marketData = uiState.marketDataUi)
            }
        }
    }
}