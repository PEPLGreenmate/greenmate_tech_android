package com.pepl.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class DefaultGardenDataSource @Inject constructor(
    @Named("garden") private val dataStore: DataStore<Preferences>,
) : GardenDataSource {

    object PreferencesKey {
        val LAST_CHECKED_GARDEN = stringPreferencesKey("LAST_CHECKED_GARDEN")
    }

    override val lastGardenId = dataStore.data.map { preferences ->
        preferences[PreferencesKey.LAST_CHECKED_GARDEN] ?: "회사 책상 위"
    }

    override suspend fun updateLastGardenId(gardenId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.LAST_CHECKED_GARDEN] = gardenId
        }
    }
}