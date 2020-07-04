package com.netguru.randomcity.core

interface Mapper<Input, Output> {

    fun map(input: Input): Output
}