package com.netguru.randomcity.producer

import com.netguru.randomcity.core.reactive.SchedulersProvider
import com.netguru.randomcity.producer.data.CityProducerEntity
import com.netguru.randomcity.producer.data.Color
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class IntervalCityProducer @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val producerDeliveryStrategy: ProducerDeliveryStrategy
) : CityProducer {

    companion object {
        private const val INTERVAL_SECONDS = 5L
        private val cities =
            listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
        private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")
    }

    private val producer =
        Observable.interval(INTERVAL_SECONDS, TimeUnit.SECONDS, schedulersProvider.computation)
            .filter { producerDeliveryStrategy.shouldDeliverValue() }
            .publish()

    override fun startCityProduction(): Observable<CityProducerEntity> =
        producer.autoConnect()
            .map {
                CityProducerEntity(
                    uuid = UUID.randomUUID().toString(),
                    cityName = cities.random(),
                    color = Color.from(colors.random())
                )
            }
}