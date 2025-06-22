package com.samples.horseracing.feature.history.useCase

import com.samples.horseracing.core.datastore.HorseRacingPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetHistoryUseCase @Inject constructor(private val dataSource: HorseRacingPreferences) {

    val historyRaces = dataSource.historyTimeRaces
}