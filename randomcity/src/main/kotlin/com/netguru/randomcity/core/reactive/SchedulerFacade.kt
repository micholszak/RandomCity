package com.netguru.randomcity.core.reactive

import io.reactivex.Observable

interface SchedulerFacade {

    fun <Data> subscribe(
        subscriber: Any,
        source: Observable<Data>,
        onNext: (Data) -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (Throwable) -> Unit = {}
    )

    fun unsubscribe(subscriber: Any)
}