package com.netguru.randomcity.producer

import com.netguru.randomcity.core.reactive.SchedulersProvider
import com.netguru.randomcity.producer.model.CityProducerEntity
import com.netguru.randomcity.producer.model.Color
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class IntervalCityProducer @Inject constructor(
    schedulersProvider: SchedulersProvider
) : CityProducer {

    companion object {
        private const val INTERVAL_SECONDS = 5L
        private val cities =
            listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
        private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")
    }

    private val producer =
        Observable.interval(INTERVAL_SECONDS, TimeUnit.SECONDS, schedulersProvider.computation)
            .publish()

    override fun startCityProduction(): Observable<CityProducerEntity> {
        return producer.autoConnect().map {
            CityProducerEntity(
                uuid = UUID.randomUUID().toString(),
                cityName = cities.random(),
                color = Color.from(colors.random())
            )
        }
    }
}