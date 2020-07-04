package com.netguru.randomcity.dependency

import com.netguru.randomcity.MainActivity
import com.netguru.randomcity.dependency.internal.MapperModule
import com.netguru.randomcity.dependency.internal.ProducerModule
import com.netguru.randomcity.dependency.internal.ReactiveModule
import com.netguru.randomcity.dependency.internal.cities.CitiesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ProducerModule::class,
        ReactiveModule::class,
        MapperModule::class
    ]
)
abstract class RandomCityDependenciesModule {

    @ContributesAndroidInjector(modules = [CitiesModule::class])
    abstract fun bindsMainActivity(): MainActivity
}