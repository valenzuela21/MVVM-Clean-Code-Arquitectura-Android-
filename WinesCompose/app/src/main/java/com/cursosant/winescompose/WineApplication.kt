package com.cursosant.winescompose

import android.app.Application
import com.cursosant.winescompose.common.di.dataSourceModule
import com.cursosant.winescompose.homeModule.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WineApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WineApplication)
            modules(dataSourceModule, homeModule)
        }
    }
}