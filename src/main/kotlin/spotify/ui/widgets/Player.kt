package spotify.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import spotify.components.widgets.Player
import spotify.ui.resources.fontAwesomeFamily
import spotify.ui.util.time
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PlayerUi(component: Player, modifier: Modifier = Modifier) {
    val state = component.state.subscribeAsState()

    if (state.value.currentTrack != null) {
        Row(modifier.defaultMinSize(minHeight = 72.dp).padding(16.dp, 0.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
            TextButton({}){
                Text("heart", fontFamily = fontAwesomeFamily,
                    fontWeight = if (state.value.favourite) FontWeight.W900 else FontWeight.W400)
            }

            Column(Modifier.weight(0.6f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
                Row(Modifier, Arrangement.Center, Alignment.CenterVertically) {
                    TextButton({}){
                        Text("shuffle", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                    }
                    TextButton({}){
                        Text("backward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                    }
                    TextButton(component::switchPlay){
                        Text(if (state.value.playing) "circle-pause" else "circle-play", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                    }
                    TextButton({}){
                        Text("forward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                    }
                    TextButton({}){
                        Text("repeat", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                    }
                }
                Row(Modifier, Arrangement.SpaceAround, Alignment.CenterVertically) {
                    Text(time(state.value.position))
                    Slider(
                        state.value.position.inWholeMilliseconds.toFloat(),
                        component::seek,
                        Modifier.width(512.dp), true,
                        0f..state.value.currentTrack!!.duration.toFloat()
                    )
                    Text(time(state.value.currentTrack!!.duration.milliseconds))
                }
            }

            TextButton({}){
                Text("microphone", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
            }
            TextButton({}){
                Text("list", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
            }
            TextButton({}){
                Text("computer", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
            }
            TextButton(component::switchMute){
                Text(
                    when(state.value.volume) {
                        in 0.66f..1f -> "volume-high"
                        in 0.33f..0.65f -> "volume-low"
                        0f -> "volume-xmark"
                        else -> "volume-off"
                    },
                    fontFamily = fontAwesomeFamily,
                    fontWeight = FontWeight.W900)
            }
            Slider(state.value.volume, component::setVolume, Modifier.width(120.dp))
        }
    }
}