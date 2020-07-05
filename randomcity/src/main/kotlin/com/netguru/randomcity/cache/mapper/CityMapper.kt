package com.netguru.randomcity.cache.mapper

import com.netguru.randomcity.cache.City
import com.netguru.randomcity.cache.dao.CityEntity
import com.netguru.randomcity.core.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CityMapper @Inject constructor() : Mapper<CityEntity, City> {

    override fun map(input: CityEntity): City =
        City(
            id = input.id,
            name = input.name,
            color = input.color,
            timestamp = input.createdTime
        )
}