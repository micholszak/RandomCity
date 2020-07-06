package com.netguru.randomcity.cache.data

import com.netguru.randomcity.producer.data.Color
import java.time.LocalDateTime

data class City(
    val id: Long = 0L,
    val name: String = "",
    val color: Color = Color.UNDEFINED,
    val timestamp: LocalDateTime = LocalDateTime.MIN
)