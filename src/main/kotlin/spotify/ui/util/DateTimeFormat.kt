package spotify.ui.util

import kotlin.time.Duration

fun time(duration: Duration): String {
    val hours = duration.inWholeHours
    val minutes = duration.inWholeMinutes
    val seconds = duration.inWholeSeconds
    return (if (hours > 0) "%02d:".format(hours) else "") + "%02d:%02d".format(minutes, seconds)
}