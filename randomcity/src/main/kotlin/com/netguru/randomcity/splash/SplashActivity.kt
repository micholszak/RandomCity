package com.netguru.randomcity.splash

import android.os.Bundle
import android.widget.TextView
import com.netguru.randomcity.BuildConfig
import com.netguru.randomcity.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(), SplashContract.View {

    @Inject
    internal lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupVersion()
        presenter.onViewCreated(this)
    }

    override fun onDestroy() {
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    private fun setupVersion() {
        val version: TextView = findViewById(R.id.version)
        version.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }
}