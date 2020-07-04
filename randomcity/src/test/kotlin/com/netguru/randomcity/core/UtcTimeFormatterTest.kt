package com.netguru.randomcity.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.time.LocalDateTime
import java.time.Month
import java.util.stream.Stream

class UtcTimeFormatterTest {

    private val formatter = UtcTimeFormatter()

    @ParameterizedTest
    @ArgumentsSource(FormatArgumentsSource::class)
    fun `Format date time from {0} to {1}`(date: LocalDateTime, expectedOutput: String) {
        val output = formatter.format(date)
        assertThat(output).isEqualTo(expectedOutput)
    }

    object FormatArgumentsSource : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            Stream.of(
                Arguments.arguments(produceLocalDateTime(), "2000-01-01 00:00:00"),
                Arguments.arguments(produceLocalDateTime(
                    year = 2020,
                    month = Month.JULY,
                    day = 3,
                    hour = 20
                ), "2020-07-03 20:00:00"),
                Arguments.arguments(produceLocalDateTime(
                    hour = 16,
                    minute = 30,
                    second = 23
                ), "2000-01-01 16:30:23")
            )

        private fun produceLocalDateTime(
            year: Int = 2000,
            month: Month = Month.JANUARY,
            day: Int = 1,
            hour: Int = 0,
            minute: Int = 0,
            second: Int = 0
        ): LocalDateTime =
            LocalDateTime.of(year, month, day, hour, minute, second)
    }
}