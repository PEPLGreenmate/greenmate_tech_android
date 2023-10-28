package com.pepl.data.di

import com.pepl.data.repository.ChatRepository
import com.pepl.data.repository.DefaultChatRepository
import com.pepl.data.repository.DefaultPlantRepository
import com.pepl.data.repository.PlantRepository
import com.pepl.datastore.datasource.DefaultPlantDataSource
import com.pepl.datastore.datasource.PlantDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindsChatRepository(
        repository: DefaultChatRepository,
    ): ChatRepository

    @Binds
    abstract fun bindsPlantRepository(
        repository: DefaultPlantRepository,
    ): PlantRepository

    @Binds
    abstract fun bindsPlantDataSource(
        dataSource: DefaultPlantDataSource,
    ): PlantDataSource

}