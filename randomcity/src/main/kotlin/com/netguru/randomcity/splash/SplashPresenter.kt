package com.netguru.randomcity.splash

import com.netguru.randomcity.core.reactive.SchedulerFacade
import com.netguru.randomcity.producer.CityProducer
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val navigateToMainActivity: NavigateToMainActivity,
    private val schedulerFacade: SchedulerFacade,
    private val cityProducer: CityProducer
) : SplashContract.Presenter {

    override fun onViewCreated(view: SplashContract.View) {
        schedulerFacade.subscribe(
            subscriber = this,
            source = cityProducer.startCityProduction(),
            onNext = {
                navigateToMainActivity()
            }
        )
    }

    override fun onViewDestroyed() {
        schedulerFacade.unsubscribe(this)
    }
}