package com.netguru.randomcity.home

import android.os.Bundle
import android.os.PersistableBundle
import com.netguru.randomcity.R
import dagger.android.DaggerActivity

class MainActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }
}