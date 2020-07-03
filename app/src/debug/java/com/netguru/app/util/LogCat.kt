package com.netguru.app.util

import android.util.Log
import com.netguru.randomcity.util.Logger
import javax.inject.Inject

class LogCat @Inject constructor() : Logger {

    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}