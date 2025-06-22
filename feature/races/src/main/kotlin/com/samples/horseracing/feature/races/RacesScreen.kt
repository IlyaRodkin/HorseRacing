package com.samples.horseracing.feature.races

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.samples.horseracing.core.model.formatMillisToMinutesSeconds
import com.samples.horseracing.feature.races.viewModel.RacesViewModel

@Composable
internal fun RacesScreen(viewModel: RacesViewModel = hiltViewModel()) {
    val state by viewModel.raceState.collectAsStateWithLifecycle()
    val time by viewModel.time.collectAsStateWithLifecycle()
    RacesScreen(
        state = state,
        timeInMillis = time,
        onClickStart = viewModel::onStart,
        onClickStop = viewModel::onStop,
    )
}

@Composable
private fun RacesScreen(
    state: RaceUiState,
    timeInMillis: Long,
    onClickStart: () -> Unit,
    onClickStop: () -> Unit
) {
    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {

            RaceViewGif(
                modifier = Modifier.align(Alignment.Center),
                isRacing = state is RaceUiState.Starting
            )

            RaceTimeTitle(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp),
                timeInMillis = timeInMillis
            )

            when (state) {
                RaceUiState.Starting ->
                    StopRaceButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp),
                        onClick = onClickStop
                    )

                RaceUiState.Stop ->
                    StartRaceButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp),
                        onClick = onClickStart
                    )
            }
        }

    }
}

@Composable
private fun RaceTimeTitle(modifier: Modifier = Modifier, timeInMillis: Long) {
    Text(
        modifier = modifier,
        text = timeInMillis.formatMillisToMinutesSeconds(),
        style = MaterialTheme.typography.displayMedium
    )
}

@Composable
private fun RaceViewGif(modifier: Modifier = Modifier, isRacing: Boolean) {
    val context = LocalContext.current
    val gifEnabledLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()
    }
    AnimatedVisibility(
        isRacing,
        modifier = modifier
            .width(480.dp)
            .height(362.dp),
    ) {
        AsyncImage(
            modifier = Modifier,
            imageLoader = gifEnabledLoader,
            model = R.drawable.giphy,
            contentDescription = "raceGif"
        )
    }
    if (!isRacing)
        AsyncImage(
            modifier = modifier
                .width(480.dp)
                .height(362.dp),
            model = R.drawable.giphy,
            contentDescription = "raceGif"
        )
}

@Composable
private fun StartRaceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = stringResource(R.string.feature_races_start_button_race))
    }
}

@Composable
private fun StopRaceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick) {
        Text(text = stringResource(R.string.feature_races_stop_button_race))
    }
}