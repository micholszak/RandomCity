package com.netguru.randomcity.cache

import com.netguru.randomcity.cache.dao.CityDao
import com.netguru.randomcity.cache.dao.CityEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.atomic.AtomicLong

class InMemoryCityDao : CityDao {

    private val entitiesSubject: Subject<List<CityEntity>> =
        BehaviorSubject.create()
    private val entities: MutableList<CityEntity> = mutableListOf()
    private val nextId = AtomicLong(entities.getInitialId())

    override fun insert(entity: CityEntity) {
        entities.add(entity.apply {
            id = nextId.incrementAndGet()
        })
        entitiesSubject.onNext(entities)
    }

    override fun getAll(): Flowable<List<CityEntity>> =
        entitiesSubject.toFlowable(BackpressureStrategy.LATEST)

    private fun List<CityEntity>.getInitialId() =
        maxBy(CityEntity::id)?.id ?: 0L
}