package com.netguru.randomcity.dependency

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule

@Module(
    includes = [
        AndroidInjectionModule::class
    ]
)
class AndroidModule {

    @Provides
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}