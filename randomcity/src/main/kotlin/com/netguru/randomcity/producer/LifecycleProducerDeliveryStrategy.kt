package com.netguru.randomcity.producer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.netguru.randomcity.core.ApplicationLifecycleOwner
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LifecycleProducerDeliveryStrategy @Inject constructor(
    applicationLifecycleOwner: ApplicationLifecycleOwner
) : ProducerDeliveryStrategy,
    LifecycleObserver {

    private val isApplicationRunning: AtomicBoolean = AtomicBoolean(false)

    init {
        applicationLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun shouldDeliverValue(): Boolean =
        isApplicationRunning.get()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun applicationWentToForeground() {
        isApplicationRunning.set(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun applicationWentToBackground() {
        isApplicationRunning.set(false)
    }
}