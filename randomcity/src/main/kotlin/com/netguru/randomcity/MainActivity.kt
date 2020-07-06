package com.netguru.randomcity

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationHost: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
        navController = navigationHost?.navController
        navController?.apply {
            addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.map_fragment -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    else -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController?.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}