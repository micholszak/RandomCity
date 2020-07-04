package com.netguru.randomcity.dependency.internal

import com.netguru.randomcity.cities.data.City
import com.netguru.randomcity.cities.data.CityColor
import com.netguru.randomcity.cities.mapper.CityColorMapper
import com.netguru.randomcity.cities.mapper.CityMapper
import com.netguru.randomcity.core.Mapper
import com.netguru.randomcity.core.TimeFormatter
import com.netguru.randomcity.core.UtcTimeFormatter
import com.netguru.randomcity.producer.data.CityProducerEntity
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
    abstract fun bindCityMapper(mapper: CityMapper): Mapper<CityProducerEntity, City>
}