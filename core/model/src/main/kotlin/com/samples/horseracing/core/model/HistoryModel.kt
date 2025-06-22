package com.samples.horseracing.core.model

import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Serializable
data class HistoryModel(
    val finishTimeRaceInMillis: Long,
    val raceTimeInMillis: Long
){
    val raceTimeInMillisFormatted
        get() = raceTimeInMillis.formatMillisToMinutesSeconds()

    val startTimeInMillisFormatted: String?
        get() = Instant.ofEpochMilli(finishTimeRaceInMillis)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))

}

fun Long.formatMillisToMinutesSeconds(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d:%03d", minutes, seconds, this%1000)
}