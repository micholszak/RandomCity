package com.netguru.randomcity.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.netguru.randomcity.core.ApplicationLifecycleOwner

class TestApplicationLifecycleOwner(
    initialState: Lifecycle.State = Lifecycle.State.INITIALIZED
) : ApplicationLifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this).apply {
        currentState = initialState
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    fun handleEvent(event: Lifecycle.Event) {
        lifecycleRegistry.handleLifecycleEvent(event)
    }
}