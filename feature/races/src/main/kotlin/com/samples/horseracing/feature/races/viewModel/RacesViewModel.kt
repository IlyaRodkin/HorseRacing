package com.samples.horseracing.feature.races.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.horseracing.feature.races.RaceUiState
import com.samples.horseracing.feature.races.useCase.AddRaceTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RacesViewModel @Inject constructor(
    private val historyTimeUseCase: AddRaceTimeUseCase
) : ViewModel() {

    val _raceState: MutableStateFlow<RaceUiState> = MutableStateFlow(RaceUiState.Stop)
    val raceState: StateFlow<RaceUiState> = _raceState.asStateFlow()

    val _time: MutableStateFlow<Long> = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time.asStateFlow()

    private var timerJob: Job? = null

    fun onStart() {
        _raceState.update { RaceUiState.Starting }
        val startTime = System.currentTimeMillis()
        timerJob = viewModelScope.launch {
            while (isActive) {
                val timeInMillis = System.currentTimeMillis() - startTime

                _time.update { timeInMillis }

                delay(16) // ≈60 FPS
            }
        }
    }

    fun onStop() = viewModelScope.launch {
        timerJob?.cancel()
        historyTimeUseCase.addHistory(time.value)
        _raceState.update { RaceUiState.Stop }
    }
}