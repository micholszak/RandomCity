package com.netguru.randomcity.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun FragmentManager.navigate(@IdRes containerId: Int, fragment: Fragment) {
    val rootFrame = findFragmentById(containerId)
    beginTransaction().apply {
        if (rootFrame != null) {
            hide(rootFrame)
        }
        add(containerId, fragment)
        addToBackStack(null)
    }.commit()
}

fun FragmentManager.replace(@IdRes containerId: Int, fragment: Fragment) {
    beginTransaction()
        .replace(containerId, fragment)
        .commit()
}

fun FragmentManager?.navigateBack(): Boolean {
    if (this != null && backStackEntryCount > 0) {
        popBackStack()
        return true
    }
    return false
}