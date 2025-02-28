package com.netguru.randomcity.cache.dao

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.netguru.randomcity.producer.data.Color
import java.time.LocalDateTime

@Keep
@Entity(tableName = "cities")
data class CityEntity(
    val name: String,
    val color: Color,
    val createdTime: LocalDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}