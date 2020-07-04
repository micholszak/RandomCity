package com.netguru.randomcity.core

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UtcTimeFormatter @Inject constructor(): TimeFormatter {

    companion object {
        private const val UTC_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private val FORMATTER = DateTimeFormatter.ofPattern(UTC_FORMAT)
    }

    override fun format(dateTime: LocalDateTime): String =
        FORMATTER.format(dateTime)
}