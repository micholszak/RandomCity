package com.netguru.app

import com.netguru.app.dependency.ApplicationComponent
import com.netguru.app.dependency.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class RandomCityApplication : DaggerApplication() {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<RandomCityApplication>

    override fun onCreate() {
        val component: ApplicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        component.inject(this)
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector
}