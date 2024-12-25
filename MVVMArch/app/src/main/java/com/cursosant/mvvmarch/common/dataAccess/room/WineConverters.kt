package com.cursosant.mvvmarch.common.dataAccess.room

import androidx.room.TypeConverter
import com.cursosant.mvvmarch.common.entities.Rating
import com.google.gson.Gson

class WineConverters {
    @TypeConverter
    fun fromJsonStr(value: String?): Rating? {
        return value?.let { Gson().fromJson(it, Rating::class.java) }
    }

    @TypeConverter
    fun fromRating(value: Rating?): String? {
        return value?.let { Gson().toJson(it) }
    }
}