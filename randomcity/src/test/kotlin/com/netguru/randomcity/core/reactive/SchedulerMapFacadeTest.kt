package com.netguru.randomcity.core.reactive

import com.netguru.randomcity.util.TestSchedulersProvider
import io.reactivex.Flowable
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SchedulerMapFacadeTest {

    companion object {
        private val observables = listOf<Observable<Int>>(
            Observable.empty(),
            Observable.just(5),
            Observable.just(10)
        )
        private val flowables = listOf<Flowable<Int>>(
            Flowable.just(5),
            Flowable.empty(),
            Flowable.just(5, 6)
        )
        private const val SIZE = 5
        private const val SUBSCRIBER = "Something"
    }

    private val schedulerProvider =
        TestSchedulersProvider()
    private lateinit var facade: SchedulerMapFacade

    @BeforeEach
    fun before() {
        facade = SchedulerMapFacade(schedulerProvider)
    }

    @Test
    fun `Add observable subscriptions into disposable on subscribe`() {
        val observables = createObservables()
        observables.forEach(::facadeSubscribe)
        val disposable = facade.disposableFor(SUBSCRIBER)
        assertThat(disposable.size()).isEqualTo(SIZE)
    }

    @Test
    fun `Add flowable subscriptions into disposable on subscribe`() {
        val flowables = createFlowables()
        flowables.forEach(::facadeSubscribe)
        val disposable = facade.disposableFor(SUBSCRIBER)
        assertThat(disposable.size()).isEqualTo(SIZE)
    }

    @Test
    fun `Clear subscriptions after unsubscribe`() {
        val observables = createObservables()
        val flowables = createFlowables()
        flowables.forEach(::facadeSubscribe)
        observables.forEach(::facadeSubscribe)
        facade.unsubscribe(SUBSCRIBER)
        val disposable = facade.disposableFor(SUBSCRIBER)
        assertThat(disposable.size()).isEqualTo(0)
    }

    private fun <T> facadeSubscribe(flowable: Flowable<T>) =
        facade.subscribe(
            subscriber = SUBSCRIBER,
            source = flowable
        )

    private fun <T> facadeSubscribe(observable: Observable<T>) {
        facade.subscribe(
            subscriber = SUBSCRIBER,
            source = observable
        )
    }

    private fun createObservables(size: Int = SIZE): List<Observable<Int>> =
        List(size) { observables.random() }

    private fun createFlowables(size: Int = SIZE): List<Flowable<Int>> =
        List(size) { flowables.random() }
}