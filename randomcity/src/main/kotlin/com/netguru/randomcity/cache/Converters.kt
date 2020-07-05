package com.netguru.randomcity.cache

import androidx.room.TypeConverter
import com.netguru.randomcity.producer.data.Color
import java.time.LocalDateTime

internal class Converters {

    @TypeConverter
    fun toColor(colorOrdinal: Int): Color = Color.values()[colorOrdinal]

    @TypeConverter
    fun fromColor(color: Color): Int = color.ordinal

    @TypeConverter
    fun fromLocalDateTime(time: LocalDateTime): String = time.toString()

    @TypeConverter
    fun toLocalDateTime(time: String): LocalDateTime = LocalDateTime.parse(time)
}