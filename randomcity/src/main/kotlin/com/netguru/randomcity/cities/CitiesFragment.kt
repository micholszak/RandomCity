package com.netguru.randomcity.cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netguru.randomcity.R
import com.netguru.randomcity.cities.adapter.CityAdapterFactory
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.core.view.ItemAdapter
import com.netguru.randomcity.map.MapArguments
import com.netguru.randomcity.map.MapFragment
import com.netguru.randomcity.util.Logger
import com.netguru.randomcity.util.navigate
import com.netguru.randomcity.util.replace
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CitiesFragment : Fragment(), CitiesContract.View {

    companion object {
        private const val TAG = "CITIES"
    }

    @Inject
    internal lateinit var presenter: CitiesContract.Presenter
    @Inject
    internal lateinit var cityAdapterFactory: CityAdapterFactory
    @Inject
    internal lateinit var logger: Logger

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
        citiesContainer = view.findViewById(R.id.cities_container)
        setupContainer()
        logger.log(TAG, "View created")
        presenter.viewCreated(this)
    }

    override fun onResume() {
        super.onResume()
        logger.log(TAG, "View resumed")
        presenter.viewAvailable()
    }

    override fun onPause() {
        super.onPause()
        logger.log(TAG, "View paused")
        presenter.viewUnavailable()
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.log(TAG, "View destroyed")
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
        val arguments = MapArguments(cityName = item.name, cityColor = item.textColor)
        val mapFragment = MapFragment.newInstance(arguments)
        if (childFragmentManager.backStackEntryCount == 0) {
            childFragmentManager.navigate(R.id.cities_nav_container, mapFragment)
        } else {
            childFragmentManager.replace(R.id.cities_nav_container, mapFragment)
        }
    }
}