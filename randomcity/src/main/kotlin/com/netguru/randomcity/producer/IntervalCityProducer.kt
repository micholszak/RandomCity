package com.netguru.randomcity.producer

import com.netguru.randomcity.core.reactive.SchedulersProvider
import com.netguru.randomcity.producer.data.Color
import com.netguru.randomcity.producer.data.ProducerEntity
import io.reactivex.Observable
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
            .autoConnect()

    override fun startCityProduction(): Observable<ProducerEntity> =
        producer.map {
            ProducerEntity(
                cityName = cities.random(),
                color = Color.from(colors.random())
            )
        }
}