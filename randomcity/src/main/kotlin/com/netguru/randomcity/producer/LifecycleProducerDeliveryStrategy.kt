package com.netguru.randomcity.producer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.netguru.randomcity.core.ApplicationLifecycleOwner
import com.netguru.randomcity.core.application.ApplicationInitializer
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LifecycleProducerDeliveryStrategy @Inject constructor(
    private val applicationLifecycleOwner: ApplicationLifecycleOwner
) : ProducerDeliveryStrategy,
    LifecycleObserver, ApplicationInitializer {

    private val isApplicationRunning: AtomicBoolean = AtomicBoolean(false)

    override fun initializeApplication() {
        applicationLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun shouldDeliverValue(): Boolean =
        isApplicationRunning.get()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun applicationWentToForeground() {
        isApplicationRunning.set(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun applicationWentToBackground() {
        isApplicationRunning.set(false)
    }
}