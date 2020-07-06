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

fun FragmentManager.replaceFragment(@IdRes containerId: Int, fragment: Fragment) {
    beginTransaction()
        .replace(containerId, fragment)
        .commit()
}

fun FragmentManager.navigateBack() {
    if (backStackEntryCount > 0) {
        popBackStack()
    }
}

fun FragmentManager.clearBackStack() {
    popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}