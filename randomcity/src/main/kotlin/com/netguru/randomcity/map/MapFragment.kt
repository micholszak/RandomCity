package com.netguru.randomcity.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.netguru.randomcity.R
import com.netguru.randomcity.util.navigateBack
import com.netguru.randomcity.util.replace

class MapFragment : Fragment(), OnMapReadyCallback {

    companion object {
        private const val MAP_KEY = "arguments_map"
        private const val ZOOM_LEVEL = 12f

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val supportMapFragment = SupportMapFragment()
        supportMapFragment.getMapAsync(this)
        childFragmentManager.replace(R.id.map_container, supportMapFragment)
        setupHeader(view)
    }

    override fun onMapReady(map: GoogleMap) {
        val name = mapArguments?.cityName.orEmpty()
        val position = CoordinatesProvider.provideFor(name)
        if (position != null) {
            map.apply {
                val options = MarkerOptions()
                    .position(position)
                    .title(mapArguments?.cityName)

                addMarker(options)
                moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL))
                uiSettings.isScrollGesturesEnabled = false
            }
        }
    }

    private fun setupHeader(view: View) {
        val header: Toolbar? = view.findViewById(R.id.header)
        if (header != null) {
            mapArguments?.let {
                header.title = it.cityName
                header.setBackgroundColor(it.cityColor)
            }
            header.setNavigationOnClickListener {
                parentFragmentManager.navigateBack()
            }
        }
    }
}