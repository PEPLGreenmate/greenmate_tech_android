package com.pepl.data.di

import android.content.Context
import com.pepl.data.repository.DefaultPlantRepository
import com.pepl.data.repository.PlantRepository
import com.pepl.datastore.datasource.DefaultPlantDataSource
import com.pepl.datastore.datasource.PlantDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsPlantRepository(
        repository: DefaultPlantRepository,
    ): PlantRepository

    @Binds
    abstract fun bindsPlantDataSource(
        dataSource: DefaultPlantDataSource,
    ): PlantDataSource

}