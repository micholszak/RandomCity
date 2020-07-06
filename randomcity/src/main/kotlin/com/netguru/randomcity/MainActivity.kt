package com.netguru.randomcity

import android.os.Bundle
import android.view.MenuItem
import com.netguru.randomcity.cities.CitiesFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, CitiesFragment())
            .commit()
        supportFragmentManager.addOnBackStackChangedListener {
            val shouldDisplayBack = supportFragmentManager.backStackEntryCount > 0
            supportActionBar?.setDisplayHomeAsUpEnabled(shouldDisplayBack)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportFragmentManager.apply {
                if (backStackEntryCount > 0) {
                    popBackStack()
                }
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}