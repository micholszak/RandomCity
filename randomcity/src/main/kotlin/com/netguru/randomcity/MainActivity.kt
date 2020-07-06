package com.netguru.randomcity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.netguru.randomcity.cities.CitiesFragment
import com.netguru.randomcity.util.navigateBack
import com.netguru.randomcity.util.replace
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val isTabletLandscape
        get() = resources.getBoolean(R.bool.isTabletLandscape)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.replace(R.id.main_container, CitiesFragment())
        }
    }

    override fun onBackPressed() {
        var handled = false
        if (isTabletLandscape.not()) {
            val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.main_container)
            val childFragmentManager = fragment?.childFragmentManager
            handled = childFragmentManager.navigateBack()
        }

        if (handled.not()) {
            super.onBackPressed()
        }
    }
}