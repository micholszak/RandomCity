package com.netguru.randomcity.producer

import com.netguru.randomcity.util.TestSchedulersProvider
import com.netguru.randomcity.producer.data.Color
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import java.util.concurrent.TimeUnit

class IntervalCityProducerTest {

    private val expectedCities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val testScheduler = TestScheduler()
    private val schedulersProvider =
        TestSchedulersProvider(computation = testScheduler)
    private val mockDeliveryStrategy: ProducerDeliveryStrategy = mock()
    private lateinit var systemUnderTest: IntervalCityProducer

    @BeforeEach
    fun setup() {
        systemUnderTest = IntervalCityProducer(
            schedulersProvider = schedulersProvider,
            producerDeliveryStrategy = mockDeliveryStrategy
        )
    }

    @Test
    fun `Producer starts without emission`() {
        val testObservable = systemUnderTest.startCityProduction().test()
        testObservable.assertNoValues()
    }

    @Test
    fun `Use strategy to determine whether deliver value`() {
        systemUnderTest.startCityProduction().test()
        testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
        verify(mockDeliveryStrategy).shouldDeliverValue()
    }

    @Nested
    @DisplayName("Producer should deliver values")
    inner class ShouldDeliverValues {

        @BeforeEach
        fun setup() {
            given{ mockDeliveryStrategy.shouldDeliverValue() }.willReturn(true)
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

    @Nested
    @DisplayName("Producer should not deliver values")
    inner class ShouldNotDeliverValues {

        @BeforeEach
        fun setup() {
            given { mockDeliveryStrategy.shouldDeliverValue() }.willReturn(false)
        }

        @Test
        fun `No values generated after interval time`() {
            val testObservable = systemUnderTest.startCityProduction().test()
            testScheduler.advanceTimeBy(5L, TimeUnit.SECONDS)
            testObservable.assertNoValues()
        }

    }
}