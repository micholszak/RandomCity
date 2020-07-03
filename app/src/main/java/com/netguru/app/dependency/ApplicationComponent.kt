package com.netguru.app.dependency

import android.app.Application
import com.netguru.app.RandomCityApplication
import com.netguru.randomcity.dependency.AndroidModule
import com.netguru.randomcity.dependency.LoggingMediatorModule
import com.netguru.randomcity.dependency.RandomCityDependenciesModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RandomCityDependenciesModule::class,
        AndroidModule::class,
        LoggingMediatorModule::class,
        LoggersModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: RandomCityApplication)
}