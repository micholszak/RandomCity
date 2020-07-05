package com.netguru.app

import com.netguru.app.dependency.DaggerApplicationComponent
import com.netguru.randomcity.core.application.ApplicationInitializer
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class RandomCityApplication : DaggerApplication() {

    @Inject
    lateinit var initializerSet: Set<@JvmSuppressWildcards ApplicationInitializer>

    override fun onCreate() {
        super.onCreate()
        initializerSet.forEach(ApplicationInitializer::initializeApplication)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.factory().create(this)
}