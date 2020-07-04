package com.netguru.randomcity.cities.mapper

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.netguru.randomcity.R
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.producer.data.Color
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CityColorMapper @Inject constructor(
    private val context: Context
) : Mapper<Color, CityColor> {

    override fun map(input: Color): CityColor {
        val colorInt = when (input) {
            Color.YELLOW -> getColor(R.color.yellow)
            Color.GREEN -> getColor(R.color.green)
            Color.BLUE -> getColor(R.color.blue)
            Color.RED -> getColor(R.color.red)
            Color.WHITE -> getColor(R.color.white)
            Color.BLACK,
            Color.UNDEFINED -> getColor(R.color.black)
        }

        return CityColor(colorInt)
    }

    private fun getColor(@ColorRes colorRes: Int) =
        ContextCompat.getColor(context, colorRes)
}