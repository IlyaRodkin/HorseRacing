package com.example.horseracing.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.horseracing.ui.HorseRacingAppState
import com.samples.horseracing.feature.history.navigation.historyScreen
import com.samples.horseracing.feature.races.navigation.RacesRoute
import com.samples.horseracing.feature.races.navigation.racesScreen

@Composable
fun HorseRacingNavHost(
    appState: HorseRacingAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = RacesRoute,
        modifier = modifier,
    ) {
        historyScreen()
        racesScreen()
    }
}