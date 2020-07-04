package com.netguru.randomcity.producer

import com.netguru.randomcity.core.reactive.TestSchedulersProvider
import com.netguru.randomcity.producer.model.Color
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class IntervalCityProducerTest {

    private val expectedCities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val testScheduler = TestScheduler()
    private val schedulersProvider = TestSchedulersProvider(computation = testScheduler)
    private lateinit var systemUnderTest: IntervalCityProducer

    @BeforeEach
    fun setup() {
        systemUnderTest = IntervalCityProducer(schedulersProvider = schedulersProvider)
    }

    @Test
    fun `Producer starts without emission`() {
        val testObservable = systemUnderTest.startCityProduction().test()
        testObservable.assertNoValues()
    }

    @Test
    fun `Generate random city value after 5 seconds`() {
        val testObservable = systemUnderTest.startCityProduction().test()
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        testObservable.values().isNotEmpty()
        val entity = testObservable.values().first()
        assertThat(entity.cityName).isIn(expectedCities)
        assertThat(entity.color).isIn(*Color.values())
    }

    @Test
    fun `Continuously generate new values`() {
        val testObservable = systemUnderTest.startCityProduction().test()
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        assertThat(testObservable.valueCount()).isEqualTo(1)
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        assertThat(testObservable.valueCount()).isEqualTo(2)
    }
}