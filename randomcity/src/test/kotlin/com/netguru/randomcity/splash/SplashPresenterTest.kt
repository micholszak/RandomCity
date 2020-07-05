package com.netguru.randomcity.splash

import com.netguru.randomcity.core.reactive.SchedulerFacade
import com.netguru.randomcity.core.reactive.SchedulerMapFacade
import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.data.ProducerEntity
import com.netguru.randomcity.util.TestSchedulersProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.jupiter.api.Test

internal class SplashPresenterTest {

    private val mockNavigation: NavigateToMainActivity = mock()
    private val mockProducer: CityProducer = mock {
        on { startCityProduction() } doReturn Observable.just(ProducerEntity())
    }
    private val schedulerFacade: SchedulerFacade = SchedulerMapFacade(TestSchedulersProvider())
    private val mockView: SplashContract.View = mock()
    private val presenter = SplashPresenter(
        navigateToMainActivity = mockNavigation,
        schedulerFacade = schedulerFacade,
        cityProducer = mockProducer
    )

    @Test
    fun `Navigate to another screen after value received`() {
        presenter.onViewCreated(mockView)
        verify(mockNavigation).invoke()
    }
}