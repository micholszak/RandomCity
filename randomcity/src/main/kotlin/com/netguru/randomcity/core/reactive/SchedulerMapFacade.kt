package com.netguru.randomcity.core.reactive

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
        onError: (Throwable) -> Unit,
        deliverOnMainThread: Boolean
    ) {
        val subscription = source
            .schedulerSupport(deliverOnMainThread)
            .subscribe(onNext, onError, onComplete)
        addSubscription(subscriber, subscription)
    }

    override fun <Data> subscribe(
        subscriber: Any,
        source: Flowable<Data>,
        onNext: (Data) -> Unit,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit,
        deliverOnMainThread: Boolean
    ) {
        val subscription = source
            .schedulerSupport(deliverOnMainThread)
            .subscribe(onNext, onError, onComplete)
        addSubscription(subscriber, subscription)
    }

    override fun unsubscribe(subscriber: Any) {
        val disposable = disposableFor(subscriber)
        disposable.clear()
        removeSubscription(subscriber)
    }

    fun disposableFor(subscriber: Any): CompositeDisposable =
        subscriptions[subscriber.toString()] ?: CompositeDisposable().also {
            subscriptions[subscriber.toString()] = it
        }

    private fun addSubscription(subscriber: Any, subscription: Disposable) {
        val disposable = disposableFor(subscriber)
        disposable.add(subscription)
    }

    private fun removeSubscription(subscriber: Any) {
        subscriptions.remove(subscriber.toString())
    }

    private fun <T> Observable<T>.schedulerSupport(mainThreadDelivery: Boolean): Observable<T> =
        subscribeOn(schedulersProvider.computation).apply {
            if (mainThreadDelivery) {
                observeOn(schedulersProvider.main)
            }
        }

    private fun <T> Flowable<T>.schedulerSupport(mainThreadDelivery: Boolean): Flowable<T> =
        subscribeOn(schedulersProvider.computation).apply {
            if (mainThreadDelivery) {
                observeOn(schedulersProvider.main)
            }
        }
}