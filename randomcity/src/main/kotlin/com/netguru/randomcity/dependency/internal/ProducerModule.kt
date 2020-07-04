package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.IntervalCityProducer
import com.netguru.randomcity.producer.LifecycleProducerDeliveryStrategy
import com.netguru.randomcity.producer.ProducerDeliveryStrategy
import dagger.Binds
import dagger.Module

@Module
internal abstract class ProducerModule {

    @Binds
    abstract fun bindProducerDeliveryStrategy(strategy: LifecycleProducerDeliveryStrategy): ProducerDeliveryStrategy

    @Binds
    abstract fun bindsCityProducer(producer: IntervalCityProducer): CityProducer
}