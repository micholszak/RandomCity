package com.netguru.randomcity.core.reactive

import io.reactivex.Scheduler

interface SchedulersProvider {
    val computation: Scheduler
    val main: Scheduler
}