package com.netguru.randomcity.core.reactive

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApplicationSchedulersProvider @Inject constructor() : SchedulersProvider {
    override val computation: Scheduler = Schedulers.computation()
    override val main: Scheduler = AndroidSchedulers.mainThread()
}