package com.netguru.randomcity.producer

import com.netguru.randomcity.producer.data.CityProducerEntity
import io.reactivex.Observable

interface CityProducer {

    fun startCityProduction(): Observable<CityProducerEntity>
}