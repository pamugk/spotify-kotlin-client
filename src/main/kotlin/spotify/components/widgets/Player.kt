package spotify.components.widgets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import spotify.api.SpotifyOAuth2Client
import spotify.api.domain.Track
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

interface Player {
    val state: Value<State>

    fun switchFavourite()
    fun switchPlay()
    fun seek(position: Float)
    fun switchMute()
    fun setVolume(volume: Float)

    data class State(
        val currentTrack: Track? = null,
        val favourite: Boolean = false,
        val playing: Boolean = false,
        val position: Duration = 0.milliseconds,
        val volume: Float = 1f
    )
}

class PlayerComponent(
    private val api: SpotifyOAuth2Client,
    componentContext: ComponentContext
): Player, ComponentContext by componentContext {
    private val _state = MutableValue(Player.State())
    override val state = _state

    private var muted: Boolean = false
    private var previousVolume: Float = 1f

    override fun switchFavourite() {
        _state.reduce { it.copy(favourite = !it.favourite) }
    }

    override fun switchPlay() {
        _state.reduce { it.copy() }
    }

    override fun seek(position: Float) {
        _state.reduce { it.copy(position = position.toDouble().milliseconds) }
    }

    override fun switchMute() {
        muted = !muted
        _state.reduce { it.copy(volume = if (muted) 0f else previousVolume) }
    }

    override fun setVolume(volume: Float) {
        previousVolume = volume
        muted = false
        _state.reduce { it.copy(volume = volume) }
    }
}