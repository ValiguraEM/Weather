package com.vali.weather.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vali.weather.model.database.dao.LocationDao
import com.vali.weather.model.database.entity.Location

@Database(entities = [Location::class], version = 3)
abstract class Database : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}