package com.netguru.randomcity.cache

import com.netguru.randomcity.cache.data.City
import io.reactivex.Flowable

interface CityRepository {

    fun getAllCities(): Flowable<List<City>>
}