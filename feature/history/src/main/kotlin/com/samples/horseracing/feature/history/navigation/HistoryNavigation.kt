package com.samples.horseracing.feature.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.samples.horseracing.feature.history.HistoryScreen
import kotlinx.serialization.Serializable

@Serializable
data object HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions) =
    navigate(route = HistoryRoute, navOptions)

fun NavGraphBuilder.historyScreen() {
    composable<HistoryRoute> {
        HistoryScreen()
    }
}
