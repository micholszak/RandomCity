package com.netguru.randomcity.dependency.internal.splash

import com.netguru.randomcity.splash.SplashContract
import com.netguru.randomcity.splash.SplashPresenter
import dagger.Binds
import dagger.Module

@Module
internal abstract class SplashPresenterModule {

    @Binds
    abstract fun bindsSplashPresenter(splashPresenter: SplashPresenter): SplashContract.Presenter
}