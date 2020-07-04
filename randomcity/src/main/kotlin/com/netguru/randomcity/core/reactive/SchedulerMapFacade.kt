package com.netguru.randomcity.core.reactive

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SchedulerMapFacade @Inject constructor(
    private val schedulersProvider: SchedulersProvider
) : SchedulerFacade {

    private val subscriptions: MutableMap<String, CompositeDisposable> = mutableMapOf()

    override fun <Data> subscribe(
        subscriber: Any,
        source: Observable<Data>,
        onNext: (Data) -> Unit,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val subscription = source
            .subscribeOn(schedulersProvider.computation)
            .observeOn(schedulersProvider.main)
            .subscribe(onNext, onError, onComplete)
        val disposable = disposableFor(subscriber)
        disposable.add(subscription)
    }

    override fun unsubscribe(subscriber: Any) {
        val disposable = disposableFor(subscriber)
        disposable.clear()
    }

    fun disposableFor(subscriber: Any): CompositeDisposable =
        subscriptions[subscriber.toString()] ?: CompositeDisposable().also {
            subscriptions[subscriber.toString()] = it
        }

    private fun removeSubscription(subscriber: Any) {
        subscriptions.remove(subscriber.toString())
    }
}