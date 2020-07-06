package com.netguru.randomcity.map

import com.google.android.gms.maps.model.LatLng

object CoordinatesProvider {
    private val coordinatesMap = mapOf(
        "Gdańsk" to LatLng(54.352025, 18.646638),
        "Warszawa" to LatLng(52.229676, 21.012229),
        "Poznań" to LatLng(52.406374, 16.925168),
        "Białystok" to LatLng(53.132489, 23.168840),
        "Wrocław" to LatLng(51.1078852, 17.0385376),
        "Katowice" to LatLng(50.264892, 19.023781),
        "Kraków" to LatLng(50.064650, 19.944980)
    )

    fun provideFor(cityName: String): LatLng? =
        coordinatesMap[cityName]
}