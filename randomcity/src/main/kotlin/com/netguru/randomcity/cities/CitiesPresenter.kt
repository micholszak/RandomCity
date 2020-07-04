package com.netguru.randomcity.cities

import com.netguru.randomcity.core.reactive.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CitiesPresenter @Inject constructor(
    private val getCities: GetCities,
    private val schedulersProvider: SchedulersProvider
) : CitiesContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun viewCreated(view: CitiesContract.View) {
        val cityDisposable = getCities()
            .subscribeOn(schedulersProvider.computation)
            .observeOn(schedulersProvider.main)
            .subscribe { city ->
                view.showCities(listOf(city))
            }
        disposable.add(cityDisposable)
    }

    override fun viewDestroyed() {
        disposable.dispose()
    }
}