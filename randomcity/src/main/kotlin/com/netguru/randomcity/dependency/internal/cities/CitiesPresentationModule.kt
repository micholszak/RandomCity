package com.netguru.randomcity.dependency.internal.cities

import com.netguru.randomcity.cities.CitiesContract
import com.netguru.randomcity.cities.CitiesPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class CitiesPresentationModule {

    @Binds
    abstract fun bindCitiesPresenter(presenter: CitiesPresenter): CitiesContract.Presenter
}