package com.netguru.randomcity.producer

interface ProducerDeliveryStrategy {

    fun shouldDeliverValue(): Boolean
}