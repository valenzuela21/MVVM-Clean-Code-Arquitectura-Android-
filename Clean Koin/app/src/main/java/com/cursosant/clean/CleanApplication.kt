package com.cursosant.clean

import android.app.Application
import com.cursosant.clean.common.mainModule
import org.koin.core.context.startKoin

class CleanApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainModule)
        }
    }

}