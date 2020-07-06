package com.netguru.randomcity

import android.os.Bundle
import android.view.MenuItem
import com.netguru.randomcity.cities.CitiesFragment
import com.netguru.randomcity.util.clearBackStack
import com.netguru.randomcity.util.navigateBack
import com.netguru.randomcity.util.replaceFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.clearBackStack()
        if (savedInstanceState == null) {
            supportFragmentManager.replaceFragment(R.id.main_container, CitiesFragment())
        } else {
            shouldDisplayBackButton()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            shouldDisplayBackButton()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportFragmentManager.navigateBack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shouldDisplayBackButton() {
        val shouldDisplayBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldDisplayBack)
    }
}