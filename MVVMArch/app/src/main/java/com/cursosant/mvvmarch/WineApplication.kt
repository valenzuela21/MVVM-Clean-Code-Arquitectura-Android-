package com.cursosant.mvvmarch

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.cursosant.mvvmarch.common.dataAccess.room.WineDatabase

class WineApplication : Application() {
    companion object {
        lateinit var database: WineDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("WineApplication", "Inicializando base de datos")
        database = Room.databaseBuilder(this,
            WineDatabase::class.java,
            "WineDatabase")
            .build()
    }
}