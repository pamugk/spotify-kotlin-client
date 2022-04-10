package spotify.ui.util

import kotlin.time.Duration

fun time(duration: Duration): String = duration.toComponents { hours, minutes, seconds, _ ->
        return (if (hours > 0) "%02d:".format(hours) else "") + "%02d:%02d".format(minutes, seconds)
    }