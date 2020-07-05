package com.netguru.randomcity.producer

import androidx.lifecycle.Lifecycle
import com.netguru.randomcity.util.TestApplicationLifecycleOwner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LifecycleProducerDeliveryStrategyTest {

    private val testLifecycleOwner = TestApplicationLifecycleOwner()
    private lateinit var deliveryStrategy: LifecycleProducerDeliveryStrategy

    @BeforeEach
    fun setup() {
        deliveryStrategy = LifecycleProducerDeliveryStrategy(testLifecycleOwner)
        deliveryStrategy.initializeApplication()
    }

    @Test
    fun `Strategy initializes with false`() {
        val shouldDeliverValue = deliveryStrategy.shouldDeliverValue()
        assertThat(shouldDeliverValue).isEqualTo(false)
    }

    @Test
    fun `Value delivery enabled when lifecycle changes to started`() {
        testLifecycleOwner.handleEvent(Lifecycle.Event.ON_START)
        val shouldDeliverValue = deliveryStrategy.shouldDeliverValue()
        assertThat(shouldDeliverValue).isEqualTo(true)
    }

    @Test
    fun `Value delivery disabled when lifecycle changes to stopped`() {
        testLifecycleOwner.handleEvent(Lifecycle.Event.ON_STOP)
        val shouldDeliverValue = deliveryStrategy.shouldDeliverValue()
        assertThat(shouldDeliverValue).isEqualTo(false)
    }
}