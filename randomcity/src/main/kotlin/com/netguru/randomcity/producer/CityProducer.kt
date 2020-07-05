package com.netguru.randomcity.producer

import com.netguru.randomcity.producer.data.ProducerEntity
import io.reactivex.Observable

interface CityProducer {

    fun startCityProduction(): Observable<ProducerEntity>
}