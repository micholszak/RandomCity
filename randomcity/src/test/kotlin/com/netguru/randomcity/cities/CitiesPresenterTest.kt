package com.netguru.randomcity.cities

import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.reactive.SchedulerMapFacade
import com.netguru.randomcity.util.TestSchedulersProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CitiesPresenterTest {

    companion object {
        private val CITIES = listOf(
            CityAdapterItem()
        )
    }

    private val getCities: GetCities = mock {
        on { invoke() } doReturn Flowable.just(CITIES)
    }
    private val schedulerFacade = spy(SchedulerMapFacade(TestSchedulersProvider()))
    private val mockView: CitiesContract.View = mock()
    private lateinit var presenter: CitiesPresenter

    @BeforeEach
    fun setup() {
        presenter = CitiesPresenter(
            getCities = getCities,
            schedulerFacade = schedulerFacade
        )
    }

    @Test
    fun `Deliver values to view on available`() {
        presenter.viewCreated(mockView)
        presenter.viewAvailable()
        verify(mockView).showCities(CITIES)
    }

    @Test
    fun `Don't show anything when view was not attached`() {
        presenter.viewAvailable()
        verifyZeroInteractions(mockView)
    }

    @Test
    fun `Unsubscribe when view is not available`() {
        presenter.viewUnavailable()
        verify(schedulerFacade).disposableFor(presenter)
    }
}