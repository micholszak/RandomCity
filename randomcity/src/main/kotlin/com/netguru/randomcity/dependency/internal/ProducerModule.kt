package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.IntervalCityProducer
import dagger.Binds
import dagger.Module

@Module
internal abstract class ProducerModule {

    @Binds
    abstract fun bindsCityProducer(producer: IntervalCityProducer): CityProducer
}