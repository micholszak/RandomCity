package com.netguru.randomcity.cities

import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.cache.CityRepository
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.cities.mapper.FakeCityAdapterItemMapper
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GetCitiesTest {

    private val mockRepository: CityRepository = mock()
    private val fakeAdapterItemMapper = FakeCityAdapterItemMapper()
    private val getCities = GetCities(
        cityAdapterItemMapper = fakeAdapterItemMapper,
        cityRepository = mockRepository
    )

    @Test
    fun `Call repository for data retrieval`() {
        given { mockRepository.getAllCities() }.willReturn(Flowable.empty())
        getCities().test()
        verify(mockRepository).getAllCities()
    }

    @Test
    fun `Return results from the call`() {
        val cities = listOf(
            City(name = "Wrocław"),
            City(name = "Katowice"),
            City(name = "Hammersmith"),
            City(name = "Honolulu"),
            City(name = "Amsterdam"),
            City(name = "Żory")
        )
        given { mockRepository.getAllCities() }.willReturn(Flowable.just(cities))

        val result = getCities().test()
            .values()
            .last()
            .map(CityAdapterItem::name)

        val expected = listOf(
            "Wrocław",
            "Katowice",
            "Hammersmith",
            "Honolulu",
            "Amsterdam",
            "Żory"
        )
        assertThat(result).isEqualTo(expected)
    }
}