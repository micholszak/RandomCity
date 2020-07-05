package com.netguru.randomcity.cities.mapper

import com.netguru.randomcity.cache.City
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.Mapper

class FakeCityAdapterItemMapper : Mapper<City, CityAdapterItem> {

    companion object {
        const val TEXT_COLOR = 0
        const val CREATION_TIMESTAMP = ""
    }

    override fun map(input: City): CityAdapterItem =
        CityAdapterItem(
            id = input.id,
            name = input.name,
            textColor = TEXT_COLOR,
            creationTimestamp = CREATION_TIMESTAMP
        )
}