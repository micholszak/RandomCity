package com.netguru.randomcity.cities

import com.netguru.randomcity.cache.City
import com.netguru.randomcity.cache.CityRepository
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.cities.mapper.FakeCityAdapterItemMapper
import com.netguru.randomcity.core.reactive.SchedulerMapFacade
import com.netguru.randomcity.util.TestSchedulersProvider
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CitiesPresenterTest {

    companion object {
        private val CITIES = listOf(City())
    }

    private val mockRepository: CityRepository = mock {
        on { getAllCities() } doReturn Flowable.just(CITIES)
    }
    private val getCities = GetCities(
        cityAdapterItemMapper = FakeCityAdapterItemMapper(),
        cityRepository = mockRepository
    )
    private val schedulerFacade = SchedulerMapFacade(TestSchedulersProvider())
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
    fun `Deliver values to view on create`() {
        val captor = argumentCaptor<List<CityAdapterItem>>()
        presenter.viewCreated(mockView)
        verify(mockView).showCities(captor.capture())
        assertThat(captor.lastValue).hasSize(CITIES.size)
    }
}