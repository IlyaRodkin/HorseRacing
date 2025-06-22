package com.samples.horseracing.feature.races.useCase

import com.samples.horseracing.core.datastore.HorseRacingPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddRaceTimeUseCase @Inject constructor(
    val historySource: HorseRacingPreferences
) {
    suspend fun addHistory(raceTimeInMillis: Long){
        val currentTime = System.currentTimeMillis()
        historySource.putRaceTime(
            finishTimeRaceInMillis = currentTime,
            timeInMills = raceTimeInMillis
        )
    }
}