package com.netguru.randomcity.cities

import com.netguru.randomcity.core.reactive.SchedulerFacade
import javax.inject.Inject

internal class CitiesPresenter @Inject constructor(
    private val getCities: GetCities,
    private val schedulerFacade: SchedulerFacade
) : CitiesContract.Presenter {

    override fun viewCreated(view: CitiesContract.View) {
        schedulerFacade.subscribe(
            subscriber = this,
            source = getCities(),
            onNext = view::showCities
        )
    }

    override fun viewDestroyed() {
        schedulerFacade.unsubscribe(this)
    }
}