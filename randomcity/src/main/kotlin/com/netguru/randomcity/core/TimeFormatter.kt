package com.netguru.randomcity.core

import java.time.LocalDateTime

interface TimeFormatter {

    fun format(dateTime: LocalDateTime): String
}