package com.netguru.randomcity.cache

import androidx.lifecycle.Lifecycle
import com.netguru.randomcity.cache.mapper.CityEntityMapper
import com.netguru.randomcity.cache.mapper.CityMapper
import com.netguru.randomcity.core.reactive.SchedulerMapFacade
import com.netguru.randomcity.producer.CityProducer
import com.netguru.randomcity.producer.data.Color
import com.netguru.randomcity.producer.data.ProducerEntity
import com.netguru.randomcity.util.TestApplicationLifecycleOwner
import com.netguru.randomcity.util.TestSchedulersProvider
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

internal class LifecycleAwareCityRepositoryTest {

    companion object {
        private const val LIST_SIZE = 5
        private val PRODUCER_ENTITY = ProducerEntity(
            cityName = "Glasgov",
            color = Color.BLACK,
            emissionTime = LocalDateTime.of(2020, Month.JULY, 5, 14, 41)
        )
    }

    private val testLifecycleOwner = TestApplicationLifecycleOwner()
    private val cityEntityMapper = CityEntityMapper()
    private val cityMapper = CityMapper()
    private val mockCityProducer: CityProducer = mock()
    private val schedulerFacade = SchedulerMapFacade(TestSchedulersProvider())
    private lateinit var inMemoryCityDao: InMemoryCityDao
    private lateinit var repository: LifecycleAwareCityRepository

    @BeforeEach
    fun setup() {
        inMemoryCityDao = InMemoryCityDao()
        repository = LifecycleAwareCityRepository(
            applicationLifecycleOwner = testLifecycleOwner,
            schedulerFacade = schedulerFacade,
            cityDao = inMemoryCityDao,
            producer = mockCityProducer,
            cityEntityMapper = cityEntityMapper,
            cityMapper = cityMapper
        )
    }

    @Nested
    @DisplayName("Subscription disposal test")
    inner class SubscriptionTest {

        @BeforeEach
        fun setup() {
            given { mockCityProducer.startCityProduction() }.willReturn(Observable.empty())
            testLifecycleOwner.handleEvent(Lifecycle.Event.ON_CREATE)
        }

        @Test
        fun `Subscribe to producer values generation`() {
            verify(mockCityProducer).startCityProduction()
            val disposable = schedulerFacade.disposableFor(repository)
            assertThat(disposable.isDisposed).isEqualTo(false)
            assertThat(disposable.size()).isGreaterThan(0)
        }

        @Test
        fun `Dispose subscription on destroy`() {
            testLifecycleOwner.handleEvent(Lifecycle.Event.ON_DESTROY)
            val disposable = schedulerFacade.disposableFor(repository)
            assertThat(disposable.size()).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("Data retrieval test")
    inner class GetData {

        @BeforeEach
        fun setup() {
            given { mockCityProducer.startCityProduction() }.willReturn(generateEntities())
            testLifecycleOwner.handleEvent(Lifecycle.Event.ON_CREATE)
        }

        @Test
        fun `Return entity list from repository`() {
            val testSubscription = repository.getAllCities().test()
            val cities = testSubscription.values().last()
            assertThat(cities.size).isEqualTo(LIST_SIZE)
            cities.forEach { city ->
                assertThat(city.name).isEqualTo(PRODUCER_ENTITY.cityName)
                assertThat(city.color).isEqualTo(PRODUCER_ENTITY.color)
                assertThat(city.timestamp).isEqualTo(PRODUCER_ENTITY.emissionTime)
            }
        }

        private fun generateEntities(size: Int = LIST_SIZE): Observable<ProducerEntity> =
            Observable.fromIterable(List(size) {
                PRODUCER_ENTITY
            })
    }
}