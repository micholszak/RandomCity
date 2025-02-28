package com.netguru.randomcity.cities

import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.cache.CityRepository
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.Mapper
import io.reactivex.Flowable
import javax.inject.Inject

class GetCities @Inject constructor(
    private val cityAdapterItemMapper: Mapper<City, CityAdapterItem>,
    private val cityRepository: CityRepository
) {

    operator fun invoke(): Flowable<List<CityAdapterItem>> =
        cityRepository.getAllCities()
            .map(::mapModels)

    private fun mapModels(cities: List<City>): List<CityAdapterItem> =
        cities.map(cityAdapterItemMapper::map)
}