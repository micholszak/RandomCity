package com.netguru.randomcity.core.reactive

import io.reactivex.Flowable
import io.reactivex.Observable

interface SchedulerFacade {

    fun <Data> subscribe(
        subscriber: Any,
        source: Observable<Data>,
        onNext: (Data) -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
        deliverOnMainThread: Boolean = true
    )

    fun <Data> subscribe(
        subscriber: Any,
        source: Flowable<Data>,
        onNext: (Data) -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
        deliverOnMainThread: Boolean = true
    )

    fun unsubscribe(subscriber: Any)
}