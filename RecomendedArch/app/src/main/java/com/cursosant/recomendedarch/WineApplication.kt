package com.cursosant.mvvmarch

import android.app.Application
import androidx.room.Room
import com.cursosant.recomendedarch.accountModule.di.accountModule
import com.cursosant.recomendedarch.common.dataAccess.room.WineDatabase
import com.cursosant.recomendedarch.common.di.adapterModule
import com.cursosant.recomendedarch.common.di.dataSourceModule
import com.cursosant.recomendedarch.common.di.domainModule
import com.cursosant.recomendedarch.common.di.modelModule
import com.cursosant.recomendedarch.common.di.viewModelModule
import com.cursosant.recomendedarch.promoModule.di.promoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WineApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WineApplication)
            modules(adapterModule, viewModelModule, modelModule, domainModule, dataSourceModule, accountModule, promoModule)
        }


    }
}