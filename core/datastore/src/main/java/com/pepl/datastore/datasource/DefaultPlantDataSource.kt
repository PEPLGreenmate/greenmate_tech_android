package com.pepl.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.pepl.datastore.di.DataStoreModule
import com.pepl.datastore.model.PlantData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultPlantDataSource @Inject constructor(
    @Named("plant") private val dataStore: DataStore<Preferences>,
) : PlantDataSource {

    object PreferencesKey {
        val MAIN_PLANT_ID = stringSetPreferencesKey("MAIN_PLANT_ID")
    }

    val mainPlantId = dataStore.data.map { preferences ->
        preferences[PreferencesKey.MAIN_PLANT_ID] ?: emptySet()
    }

    suspend fun updateMainPlantId(plantId: Set<String>) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.MAIN_PLANT_ID] = plantId
        }
    }
}