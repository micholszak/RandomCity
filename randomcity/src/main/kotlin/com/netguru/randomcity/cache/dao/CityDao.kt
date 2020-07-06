package com.netguru.randomcity.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: CityEntity)

    @Query("SELECT * from cities ORDER by name")
    fun getAllSortedAlphabetically(): Flowable<List<CityEntity>>
}