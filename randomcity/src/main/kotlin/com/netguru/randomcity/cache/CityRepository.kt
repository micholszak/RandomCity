package com.netguru.randomcity.cache

import io.reactivex.Flowable

interface CityRepository {

    fun getAllCities(): Flowable<List<City>>
}