package com.netguru.app.dependency

import android.app.Application
import com.netguru.app.RandomCityApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplication(app: RandomCityApplication): Application = app
}