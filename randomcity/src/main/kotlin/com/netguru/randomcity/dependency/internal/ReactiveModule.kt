package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.core.reactive.ApplicationSchedulersProvider
import com.netguru.randomcity.core.reactive.SchedulersProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class ReactiveModule {

    @Binds
    @Singleton
    abstract fun bindSchedulersProvider(provider: ApplicationSchedulersProvider): SchedulersProvider
}