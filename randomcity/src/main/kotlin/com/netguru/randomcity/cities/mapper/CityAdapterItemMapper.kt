package com.netguru.randomcity.cities.mapper

import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.TimeFormatter
import com.netguru.randomcity.producer.data.Color
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CityAdapterItemMapper @Inject constructor(
    private val colorMapper: Mapper<Color, CityColor>,
    private val timeFormatter: TimeFormatter
) : Mapper<City, CityAdapterItem> {

    override fun map(input: City): CityAdapterItem =
        CityAdapterItem(
            id = input.id,
            name = input.name,
            textColor = colorMapper.map(input.color).colorInt,
            creationTimestamp = timeFormatter.format(input.timestamp)
        )
}