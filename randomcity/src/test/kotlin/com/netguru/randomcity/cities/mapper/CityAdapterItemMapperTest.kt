package com.netguru.randomcity.cities.mapper

import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.TimeFormatter
import com.netguru.randomcity.producer.data.Color
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class CityAdapterItemMapperTest {

    companion object {
        private val CITY = City()
    }

    private val mockTimeFormatter: TimeFormatter = mock {
        on { format(any()) } doReturn ""
    }
    private val mockColorMapper: Mapper<Color, CityColor> = mock {
        on { map(any()) } doReturn CityColor(0)
    }
    private val mapper = CityAdapterItemMapper(
        timeFormatter = mockTimeFormatter,
        colorMapper = mockColorMapper
    )

    @Test
    fun `Use timeFormatter to format date`() {
        mapper.map(CITY)
        verify(mockTimeFormatter).format(CITY.timestamp)
    }

    @Test
    fun `Use colorMapper to map color int`() {
        mapper.map(CITY)
        verify(mockColorMapper).map(CITY.color)
    }
}