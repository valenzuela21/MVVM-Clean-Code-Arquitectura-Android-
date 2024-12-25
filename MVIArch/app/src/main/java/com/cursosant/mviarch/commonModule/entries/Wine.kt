package com.cursosant.mviarch.commonModule.entries

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "WineEntity")
data class Wine(val winery: String,
                val wine: String,
                var rating: Rating,
                val location: String,
                val image: String,
                @PrimaryKey val id: Double,
                var isFavorite: Boolean = false)
