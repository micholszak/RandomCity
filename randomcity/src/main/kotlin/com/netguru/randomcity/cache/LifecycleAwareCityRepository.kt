package com.netguru.randomcity.cache

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.netguru.randomcity.cache.dao.CityDao
import com.netguru.randomcity.cache.dao.CityEntity
import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.core.ApplicationLifecycleOwner
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.application.ApplicationInitializer
import com.netguru.randomcity.core.reactive.SchedulerFacade
import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.data.ProducerEntity
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LifecycleAwareCityRepository @Inject constructor(
    private val applicationLifecycleOwner: ApplicationLifecycleOwner,
    private val schedulerFacade: SchedulerFacade,
    private val cityDao: CityDao,
    private val producer: CityProducer,
    private val cityEntityMapper: Mapper<ProducerEntity, CityEntity>,
    private val cityMapper: Mapper<CityEntity, City>
) : CityRepository, LifecycleObserver, ApplicationInitializer {

    override fun initializeApplication() {
        applicationLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun getAllCities(): Flowable<List<City>> =
        Flowable.defer {
            cityDao.getAllSortedAlphabetically()
        }.map { cityEntities ->
            cityEntities.map(cityMapper::map)
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun startObservingProducer() {
        schedulerFacade.subscribe(
            subscriber = this,
            source = producer.startCityProduction(),
            onNext = {
                cityDao.insert(cityEntityMapper.map(it))
            }
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun stopObservingProducer() {
        schedulerFacade.unsubscribe(this)
        applicationLifecycleOwner.lifecycle.removeObserver(this)
    }
}