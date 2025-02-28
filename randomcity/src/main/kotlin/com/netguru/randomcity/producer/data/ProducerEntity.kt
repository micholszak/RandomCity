package com.netguru.randomcity.producer.data

import java.time.LocalDateTime

data class ProducerEntity(
    val cityName: String = "",
    val color: Color = Color.UNDEFINED,
    val emissionTime: LocalDateTime = LocalDateTime.now()
)