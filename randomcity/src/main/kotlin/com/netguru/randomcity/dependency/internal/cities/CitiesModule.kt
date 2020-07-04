package com.netguru.randomcity.dependency.internal.cities

import com.netguru.randomcity.cities.CitiesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CitiesModule {

    @ContributesAndroidInjector(modules = [CitiesPresentationModule::class])
    abstract fun provideCitiesFragment(): CitiesFragment
}