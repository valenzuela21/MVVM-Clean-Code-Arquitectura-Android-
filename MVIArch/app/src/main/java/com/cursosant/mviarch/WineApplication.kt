package com.cursosant.mviarch

import android.app.Application
import androidx.room.Room
import com.cursosant.mviarch.commonModule.dataAccess.room.WineDatabase


class WineApplication : Application() {
    companion object {
        lateinit var database: WineDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
            WineDatabase::class.java,
            "WineDatabase")
            .build()
    }
}