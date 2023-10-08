package com.pepl.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private const val PLANT_DATASTORE_NAME = "PLANT_PREFERENCES"
    private val Context.plantDataStore by preferencesDataStore(PLANT_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named("plant")
    fun providePlantDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.plantDataStore

}
