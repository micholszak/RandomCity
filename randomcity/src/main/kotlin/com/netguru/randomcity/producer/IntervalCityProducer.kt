package com.netguru.randomcity.producer

import com.netguru.randomcity.core.reactive.SchedulersProvider
import com.netguru.randomcity.producer.model.CityProducerEntity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class IntervalCityProducer @Inject constructor(
    schedulersProvider: SchedulersProvider
) : CityProducer {

    companion object {
        private const val PRODUCTION_INTERVAL_SECONDS = 5L
        val cities =
            listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
        val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")
    }

    private val producer = Observable.interval(
        /*period*/ PRODUCTION_INTERVAL_SECONDS,
        /*unit*/ TimeUnit.SECONDS,
        /*scheduler*/ schedulersProvider.computation
    )

    override fun startCityProduction(): Observable<CityProducerEntity> =
        producer.map {
            CityProducerEntity(
                cityName = cities.random(),
                color = colors.random()
            )
        }
}