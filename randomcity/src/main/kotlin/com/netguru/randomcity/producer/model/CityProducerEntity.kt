package com.netguru.randomcity.producer.model

import java.time.LocalDateTime

data class CityProducerEntity(
    val cityName: String,
    val color: String,
    val emissionTime: LocalDateTime = LocalDateTime.now()
)