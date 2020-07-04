package com.netguru.randomcity.cities.data

import com.netguru.randomcity.core.view.AdapterItem

data class City(
    val id: String,
    val name: String,
    val textColor: Int,
    val creationTimestamp: String
) : AdapterItem {

    override fun isTheSameAs(other: Any): Boolean =
        id == (other as? City)?.id
}