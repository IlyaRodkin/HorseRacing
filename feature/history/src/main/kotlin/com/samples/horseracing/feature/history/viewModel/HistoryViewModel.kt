package com.samples.horseracing.feature.history.viewModel

import androidx.lifecycle.ViewModel
import com.samples.horseracing.feature.history.useCase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getHistory: GetHistoryUseCase) : ViewModel() {

    val history = getHistory.historyRaces

}