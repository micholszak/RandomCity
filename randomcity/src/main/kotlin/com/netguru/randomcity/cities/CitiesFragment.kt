package com.netguru.randomcity.cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netguru.randomcity.R
import com.netguru.randomcity.cities.adapter.CityAdapterFactory
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.view.ItemAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CitiesFragment : Fragment(), CitiesContract.View {

    @Inject
    internal lateinit var presenter: CitiesContract.Presenter

    @Inject
    internal lateinit var cityAdapterFactory: CityAdapterFactory

    private lateinit var citiesContainer: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_cities, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        citiesContainer = view.findViewById(R.id.city_container)
        setupContainer()
        presenter.viewCreated(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.viewDestroyed()
    }

    override fun showCities(cities: List<CityAdapterItem>) {
        adapter.items = cities
    }

    private fun setupContainer() {
        adapter = cityAdapterFactory.create(::navigateToMap)
        citiesContainer.layoutManager = LinearLayoutManager(context)
        citiesContainer.adapter = adapter
    }

    private fun navigateToMap(item: CityAdapterItem) {
        val isTabletLandscape = resources.getBoolean(R.bool.isTabletLandscape)
        if (isTabletLandscape) {
            val navHostFragment: NavHostFragment? =
                childFragmentManager.findFragmentById(R.id.cities_nav_container) as NavHostFragment?
            navHostFragment?.navController?.navigate(R.id.map_fragment)
        } else {
            findNavController().navigate(R.id.cities_to_map)
        }
    }
}