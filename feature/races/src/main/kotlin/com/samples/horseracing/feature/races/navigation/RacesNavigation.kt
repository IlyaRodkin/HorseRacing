package com.samples.horseracing.feature.races.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.samples.horseracing.feature.races.RacesScreen
import kotlinx.serialization.Serializable

@Serializable
data object RacesRoute

fun NavController.navigateToRaces(navOptions: NavOptions) =
    navigate(route = RacesRoute, navOptions)

fun NavGraphBuilder.racesScreen() {
    composable<RacesRoute> {
        RacesScreen()
    }
}
