package com.netguru.randomcity.map

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapArguments(
    val cityName: String = "",
    val cityColor: Int = 0
) : Parcelable