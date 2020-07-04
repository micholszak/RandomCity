package com.netguru.randomcity.core.view

interface AdapterItem {

    fun isTheSameAs(other: Any): Boolean

    fun hasTheSameContentAs(other: Any): Boolean = this == other
}