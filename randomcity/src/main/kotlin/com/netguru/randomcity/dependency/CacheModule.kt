package com.netguru.randomcity.dependency

import android.content.Context
import androidx.room.Room
import com.netguru.randomcity.cache.ApplicationDatabase
import com.netguru.randomcity.cache.CityRepository
import com.netguru.randomcity.cache.LifecycleAwareCityRepository
import com.netguru.randomcity.cache.dao.CityDao
import com.netguru.randomcity.core.application.ApplicationInitializer
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ApplicationDatabase =
        Room.databaseBuilder(context, ApplicationDatabase::class.java, "app.db")
            .build()

    @Provides
    fun provideCityDao(database: ApplicationDatabase): CityDao =
        database.cityDao()

    @Provides
    fun provideCityRepository(repository: LifecycleAwareCityRepository): CityRepository = repository

    @Provides
    @IntoSet
    fun provideRepositoryInitializer(repository: LifecycleAwareCityRepository): ApplicationInitializer =
        repository
}