package com.samples.horseracing.feature.races

sealed interface RaceUiState {
    data object Starting: RaceUiState
    data object Stop : RaceUiState
}