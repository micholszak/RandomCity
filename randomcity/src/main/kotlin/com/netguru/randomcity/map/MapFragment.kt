package com.netguru.randomcity.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.netguru.randomcity.R
import com.netguru.randomcity.util.navigateBack
import com.netguru.randomcity.util.replace

class MapFragment : Fragment(), OnMapReadyCallback {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_map, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            val supportMapFragment = SupportMapFragment()
            supportMapFragment.getMapAsync(this)
            childFragmentManager.replace(R.id.map_container, supportMapFragment)
        }
        setupHeader(view)
    }

    override fun onMapReady(map: GoogleMap) {
        //TODO
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