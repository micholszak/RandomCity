package com.netguru.randomcity.cities

import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.reactive.SchedulerFacade
import javax.inject.Inject

internal class CitiesPresenter @Inject constructor(
    private val getCities: GetCities,
    private val schedulerFacade: SchedulerFacade
) : CitiesContract.Presenter {

    private var view: CitiesContract.View? = null

    override fun viewCreated(view: CitiesContract.View) {
        this.view = view
    }

    override fun viewAvailable() {
        schedulerFacade.subscribe(
            subscriber = this,
            source = getCities(),
            onNext = ::showCities
        )
    }

    override fun viewUnavailable() {
        schedulerFacade.unsubscribe(this)
    }

    override fun viewDestroyed() {
        schedulerFacade.unsubscribe(this)
    }

    private fun showCities(cities: List<CityAdapterItem>) {
        view?.showCities(cities)
    }
}