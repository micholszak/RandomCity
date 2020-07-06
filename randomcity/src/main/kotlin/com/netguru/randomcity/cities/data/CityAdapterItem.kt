package com.netguru.randomcity.cities.data

import com.netguru.randomcity.core.view.AdapterItem

data class CityAdapterItem(
    val id: Long = 0L,
    val name: String = "",
    val textColor: Int = 0,
    val creationTimestamp: String = ""
) : AdapterItem {

    override fun isTheSameAs(other: Any): Boolean =
        id == (other as? CityAdapterItem)?.id
}