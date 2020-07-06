package com.netguru.randomcity.map

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : SupportMapFragment(), OnMapReadyCallback {

    companion object {
        private const val MAP_KEY = "arguments_map"

        fun newInstance(mapArguments: MapArguments): MapFragment {
            val bundle = Bundle().apply {
                putParcelable(MAP_KEY, mapArguments)
            }
            return MapFragment().apply {
                arguments = bundle
            }
        }
    }

    private var mapArguments: MapArguments? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapArguments = arguments?.getParcelable(MAP_KEY)
        getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.apply {
            mapArguments?.let {
                title = it.cityName
                setBackgroundDrawable(ColorDrawable(it.cityColor))
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        //TODO
    }
}