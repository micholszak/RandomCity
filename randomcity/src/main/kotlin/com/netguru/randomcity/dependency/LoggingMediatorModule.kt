package com.netguru.randomcity.dependency

import com.netguru.randomcity.util.Logger
import com.netguru.randomcity.util.LoggerMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class LoggingMediatorModule {

    @Multibinds
    abstract fun bindLoggers(): Set<Logger>

    @Binds
    abstract fun bindLoggerMediator(mediator: LoggerMediator): Logger
}