package com.netguru.randomcity.producer.data

import java.time.LocalDateTime

data class CityProducerEntity(
    val uuid: String,
    val cityName: String,
    val color: Color,
    val emissionTime: LocalDateTime = LocalDateTime.now()
)