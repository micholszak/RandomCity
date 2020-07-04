package com.netguru.randomcity.core.reactive

import com.netguru.randomcity.util.TestSchedulersProvider
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
    fun `Add subscriptions into disposable on subscribe`() {
        val observables = createObservables()
        observables.forEach { observable ->
            facade.subscribe(
                subscriber = SUBSCRIBER,
                source = observable
            )
        }
        val disposable = facade.disposableFor(SUBSCRIBER)
        assertThat(disposable.size()).isEqualTo(SIZE)
    }

    @Test
    fun `Clear subscriptions after unsubscribe`() {
        val observables = createObservables()
        observables.forEach { observable ->
            facade.subscribe(
                subscriber = SUBSCRIBER,
                source = observable
            )
        }
        facade.unsubscribe(SUBSCRIBER)
        val disposable = facade.disposableFor(SUBSCRIBER)
        assertThat(disposable.size()).isEqualTo(0)
    }

    private fun createObservables(size: Int = SIZE): List<Observable<Int>> =
        List(size) { observables.random() }
}