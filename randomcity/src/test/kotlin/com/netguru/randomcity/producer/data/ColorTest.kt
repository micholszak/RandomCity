package com.netguru.randomcity.producer.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class ColorTest {

    @ParameterizedTest
    @ArgumentsSource(ColorsArgumentSource::class)
    fun `{0} should convert to {1}`(colorString: String, expectedColor: Color) {
        val color = Color.from(colorString)
        assertThat(color).isEqualTo(expectedColor)
    }

    object ColorsArgumentSource : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            Stream.of(
                Arguments.arguments("Yellow", Color.YELLOW),
                Arguments.arguments("Green", Color.GREEN),
                Arguments.arguments("Blue", Color.BLUE),
                Arguments.arguments("Red", Color.RED),
                Arguments.arguments("Black", Color.BLACK),
                Arguments.arguments("White", Color.WHITE),
                Arguments.arguments("something else", Color.UNDEFINED)
            )
    }
}