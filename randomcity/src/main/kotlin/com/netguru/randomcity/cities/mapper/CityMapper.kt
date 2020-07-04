package com.netguru.randomcity.cities.mapper

import com.netguru.randomcity.cities.data.City
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.TimeFormatter
import com.netguru.randomcity.producer.data.CityProducerEntity
import com.netguru.randomcity.producer.data.Color
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CityMapper @Inject constructor(
    private val colorMapper: Mapper<Color, CityColor>,
    private val timeFormatter: TimeFormatter
) : Mapper<CityProducerEntity, City> {

    override fun map(input: CityProducerEntity): City =
        City(
            id = input.uuid,
            name = input.cityName,
            textColor = colorMapper.map(input.color).colorInt,
            creationTimestamp = timeFormatter.format(input.emissionTime)
        )
}