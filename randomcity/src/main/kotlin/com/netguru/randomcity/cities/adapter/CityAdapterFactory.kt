package com.netguru.randomcity.cities.adapter

import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.view.ItemAdapter
import javax.inject.Inject

class CityAdapterFactory @Inject constructor(private val cityDelegateFactory: CityDelegateFactory) {

    fun create(onCityClick: (CityAdapterItem) -> Unit) =
        ItemAdapter(listOf(cityDelegateFactory.create(onCityClick)))
}