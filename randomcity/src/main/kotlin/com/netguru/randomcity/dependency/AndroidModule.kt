package com.netguru.randomcity.dependency

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.netguru.randomcity.core.ApplicationLifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        AndroidInjectionModule::class
    ]
)
class AndroidModule {

    @Provides
    @Singleton
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplicationLifecycleOwner(): ApplicationLifecycleOwner {
        val processLifecycleOwner = ProcessLifecycleOwner.get()
        return object : ApplicationLifecycleOwner {
            override fun getLifecycle(): Lifecycle =
                processLifecycleOwner.lifecycle
        }
    }
}