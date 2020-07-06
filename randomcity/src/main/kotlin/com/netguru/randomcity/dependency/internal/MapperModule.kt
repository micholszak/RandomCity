package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.cache.data.City
import com.netguru.randomcity.cache.dao.CityEntity
import com.netguru.randomcity.cache.mapper.CityEntityMapper
import com.netguru.randomcity.cache.mapper.CityMapper
import com.netguru.randomcity.cities.data.CityAdapterItem
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.cities.mapper.CityAdapterItemMapper
import com.netguru.randomcity.cities.mapper.CityColorMapper
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.TimeFormatter
import com.netguru.randomcity.core.UtcTimeFormatter
import com.netguru.randomcity.producer.data.ProducerEntity
import com.netguru.randomcity.producer.data.Color
import dagger.Binds
import dagger.Module

@Module
internal abstract class MapperModule {

    @Binds
    abstract fun bindTimeFormatter(formatter: UtcTimeFormatter): TimeFormatter

    @Binds
    abstract fun bindCityColorMapper(mapper: CityColorMapper): Mapper<Color, CityColor>

    @Binds
    abstract fun bindCityAdapterItemMapper(mapper: CityAdapterItemMapper): Mapper<City, CityAdapterItem>

    @Binds
    abstract fun bindCityEntityMapper(mapper: CityEntityMapper): Mapper<ProducerEntity, CityEntity>

    @Binds
    abstract fun bindCityMapper(mapper: CityMapper): Mapper<CityEntity, City>
}