package com.samples.horseracing.feature.history

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samples.horseracing.core.model.HistoryModel
import com.samples.horseracing.feature.history.viewModel.HistoryViewModel

@Composable
internal fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val data by viewModel.history.collectAsStateWithLifecycle(initialValue = listOf())

    HistoryScreen(history = data)
}

@Composable
private fun HistoryScreen(history: List<HistoryModel>) {
    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            history.forEach { itemHistory ->
                item {
                    Column(
                        modifier = Modifier.padding(4.dp).border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Text(text = "finish date: ${itemHistory.startTimeInMillisFormatted}")
                        HorizontalDivider()
                        Text(text = "race time duration: ${itemHistory.raceTimeInMillisFormatted}")
                    }
                }
            }
        }
    }
}