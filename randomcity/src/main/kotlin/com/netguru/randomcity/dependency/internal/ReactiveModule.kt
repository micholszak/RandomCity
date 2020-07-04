package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.core.reactive.ApplicationSchedulersProvider
import com.netguru.randomcity.core.reactive.SchedulersProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ReactiveModule {

    @Binds
    abstract fun bindSchedulersProvider(provider: ApplicationSchedulersProvider): SchedulersProvider
}