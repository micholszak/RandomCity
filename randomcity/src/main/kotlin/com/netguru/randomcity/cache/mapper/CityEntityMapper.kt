package com.netguru.randomcity.cache.mapper

import com.netguru.randomcity.cache.dao.CityEntity
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.producer.data.ProducerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CityEntityMapper @Inject constructor() : Mapper<ProducerEntity, CityEntity> {

    override fun map(input: ProducerEntity): CityEntity =
        CityEntity(
            name = input.cityName,
            color = input.color,
            createdTime = input.emissionTime
        )
}