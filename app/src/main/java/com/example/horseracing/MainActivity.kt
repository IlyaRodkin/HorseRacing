package com.example.horseracing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.horseracing.ui.HorseRacingApp
import com.example.horseracing.ui.rememberHorseRacingAppState
import com.samples.horseracing.core.designsystem.theme.HorseRacingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberHorseRacingAppState()
            HorseRacingTheme {
                HorseRacingApp(appState)
            }
        }
    }
}