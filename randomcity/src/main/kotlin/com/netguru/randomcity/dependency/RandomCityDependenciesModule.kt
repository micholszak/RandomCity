package com.netguru.randomcity.dependency

import com.netguru.randomcity.dependency.internal.ProducerModule
import com.netguru.randomcity.dependency.internal.ReactiveModule
import com.netguru.randomcity.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ProducerModule::class,
        ReactiveModule::class
    ]
)
abstract class RandomCityDependenciesModule {

    @ContributesAndroidInjector
    abstract fun bindsMainActivity(): MainActivity
}