package com.netguru.app.dependency

import com.netguru.app.util.LogCat
import com.netguru.randomcity.util.Logger
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class LoggersModule {

    @Binds
    @IntoSet
    abstract fun bindLogCat(logger: LogCat): Logger
}