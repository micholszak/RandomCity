package com.netguru.randomcity.cities

import com.netguru.randomcity.cities.data.CityAdapterItem

interface CitiesContract {

    interface View {
        fun showCities(cities: List<CityAdapterItem>)
    }

    interface Presenter {

        fun viewCreated(view: View)

        fun viewDestroyed()
    }
}