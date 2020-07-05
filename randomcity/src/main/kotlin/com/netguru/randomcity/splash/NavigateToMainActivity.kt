package com.netguru.randomcity.splash

import android.app.Activity
import android.content.Intent
import com.netguru.randomcity.MainActivity
import javax.inject.Inject

class NavigateToMainActivity @Inject constructor(
    private val activity: Activity
) {

    operator fun invoke() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}