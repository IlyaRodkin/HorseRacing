package com.samples.horseracing.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.samples.horseracing.core.model.HistoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HorseRacingPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun putRaceTime(finishTimeRaceInMillis: Long, timeInMills: Long) {
        dataStore.edit { preferences ->
            val currentData =
                Json.decodeFromString<List<HistoryModel>>(preferences[HISTORY] ?: "[]")
            val newJson = Json.encodeToString(
                listOf(
                    HistoryModel(
                        finishTimeRaceInMillis = finishTimeRaceInMillis,
                        raceTimeInMillis = timeInMills
                    )
                ) + currentData
            )
            preferences[HISTORY] = newJson
        }
    }

    val  historyTimeRaces: Flow<List<HistoryModel>> =
        dataStore.data.map { preferences ->
            Json.decodeFromString<List<HistoryModel>>(preferences[HISTORY] ?: "[]")
        }

    companion object {
        val HISTORY = stringPreferencesKey("History")
    }
}