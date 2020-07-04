package com.netguru.randomcity.cities

import com.netguru.randomcity.cities.data.City

interface CitiesContract {

    interface View {
        fun showCities(cities: List<City>)
    }

    interface Presenter {

        fun viewCreated(view: View)

        fun viewDestroyed()
    }
}