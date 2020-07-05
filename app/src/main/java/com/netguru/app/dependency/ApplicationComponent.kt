package com.netguru.app.dependency

import com.netguru.app.RandomCityApplication
import com.netguru.randomcity.dependency.AndroidModule
import com.netguru.randomcity.dependency.CacheModule
import com.netguru.randomcity.dependency.LoggingMediatorModule
import com.netguru.randomcity.dependency.RandomCityDependenciesModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RandomCityDependenciesModule::class,
        ApplicationModule::class,
        AndroidModule::class,
        LoggingMediatorModule::class,
        LoggersModule::class,
        CacheModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<RandomCityApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<RandomCityApplication>
}