package com.aherbel.cryptotracker.application.di

import com.aherbel.cryptotracker.application.DecimalPercentageFormatter
import com.aherbel.cryptotracker.domain.PercentageFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FormattersModule {

    @Provides
    fun providesDecimalPercentageFormatter(): PercentageFormatter =
        DecimalPercentageFormatter()
}