package com.netguru.randomcity.cities

import com.netguru.randomcity.cities.data.City
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.data.CityProducerEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetCities @Inject constructor(
    private val cityMapper: Mapper<CityProducerEntity, City>,
    private val producer: CityProducer
) {

    operator fun invoke(): Observable<City> =
        producer.startCityProduction()
            .map(cityMapper::map)
}