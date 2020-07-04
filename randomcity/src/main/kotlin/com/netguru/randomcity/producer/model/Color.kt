package com.netguru.randomcity.producer.model

enum class Color {
    YELLOW,
    GREEN,
    BLUE,
    RED,
    BLACK,
    WHITE,
    UNDEFINED;

    companion object {
        fun from(color: String): Color =
            values().firstOrNull {
                it.name.equals(other = color, ignoreCase = true)
            } ?: UNDEFINED
    }
}