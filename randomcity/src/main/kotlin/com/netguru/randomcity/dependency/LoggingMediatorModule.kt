package com.netguru.randomcity.dependency

import com.netguru.randomcity.util.Logger
import com.netguru.randomcity.util.LoggerMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Singleton

@Module
abstract class LoggingMediatorModule {

    @Multibinds
    abstract fun bindLoggers(): Set<Logger>

    @Binds
    @Singleton
    abstract fun bindLoggerMediator(mediator: LoggerMediator): Logger
}