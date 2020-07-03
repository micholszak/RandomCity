package com.netguru.randomcity.producer

import com.netguru.randomcity.producer.model.CityProducerEntity
import io.reactivex.Observable

interface CityProducer {

    fun startCityProduction(): Observable<CityProducerEntity>
}