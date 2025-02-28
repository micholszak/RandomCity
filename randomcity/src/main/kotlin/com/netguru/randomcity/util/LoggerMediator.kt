package com.netguru.randomcity.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggerMediator @Inject constructor(
    private val loggers: Set<@JvmSuppressWildcards Logger>
) : Logger {

    override fun log(tag: String, message: String) {
        loggers.forEach {
            it.log(tag, message)
        }
    }
}