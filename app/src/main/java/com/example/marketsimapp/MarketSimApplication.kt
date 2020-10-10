package com.example.marketsimapp

import android.app.Application
import com.example.marketsimapp.di.networkModule
import com.example.marketsimapp.di.repositoryModule
import com.example.marketsimapp.di.viewModelModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MarketSimApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(listOf(viewModelModule, networkModule, repositoryModule))
        }
    }
}