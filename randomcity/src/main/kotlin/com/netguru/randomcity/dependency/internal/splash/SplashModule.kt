package com.netguru.randomcity.dependency.internal.splash

import android.app.Activity
import com.netguru.randomcity.splash.SplashActivity
import dagger.Module
import dagger.Provides

@Module(includes = [SplashPresenterModule::class])
internal class SplashModule {

    @Provides
    fun provideSplashActivity(activity: SplashActivity): Activity = activity
}