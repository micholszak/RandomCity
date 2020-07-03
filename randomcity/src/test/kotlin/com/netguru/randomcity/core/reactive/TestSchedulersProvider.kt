package com.netguru.randomcity.core.reactive

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulersProvider(
    override val computation: Scheduler = Schedulers.trampoline(),
    override val main: Scheduler = Schedulers.trampoline()
) : SchedulersProvider