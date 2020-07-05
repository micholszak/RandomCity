package com.netguru.randomcity.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.netguru.randomcity.cache.dao.CityDao
import com.netguru.randomcity.cache.dao.CityEntity

@Database(entities = [CityEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}