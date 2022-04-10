package spotify.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spotify.ui.resources.fontAwesomeFamily
import spotify.ui.util.time
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PLayer(modifier: Modifier = Modifier) {
    val isFavorite = remember { mutableStateOf(false) }
    val isPlaying = remember { mutableStateOf(false) }
    val position = remember { mutableStateOf(0.milliseconds) }
    val duration = 66000.milliseconds
    val volume = remember { mutableStateOf(1f) }

    Row(modifier.defaultMinSize(minHeight = 72.dp).padding(16.dp, 0.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        TextButton({}){
            Text("heart", fontFamily = fontAwesomeFamily,
                fontWeight = if (isFavorite.value) FontWeight.W900 else FontWeight.W400)
        }

        Column(Modifier.weight(0.6f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Row(Modifier, Arrangement.Center, Alignment.CenterVertically) {
                TextButton({}){
                    Text("shuffle", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                TextButton({}){
                    Text("backward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                TextButton({isPlaying.value = !isPlaying.value}){
                    Text(if (isPlaying.value) "circle-pause" else "circle-play", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                TextButton({}){
                    Text("forward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                TextButton({}){
                    Text("repeat", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
            }
            Row(Modifier, Arrangement.SpaceAround, Alignment.CenterVertically) {
                Text(time(position.value))
                Slider(
                    position.value.inWholeMilliseconds.toFloat(),
                    { position.value = it.toDouble().milliseconds },
                    Modifier.width(512.dp), true,
                    0f..duration.inWholeMilliseconds.toFloat()
                )
                Text(time(duration))
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
        TextButton({ volume.value = 0f }){
            Text(
                when(volume.value) {
                    in 0.66f..1f -> "volume-high"
                    in 0.33f..0.65f -> "volume-low"
                    0f -> "volume-xmark"
                    else -> "volume-off"
                },
                fontFamily = fontAwesomeFamily,
                fontWeight = FontWeight.W900)
        }
        Slider(volume.value, { volume.value = it }, Modifier.width(120.dp))
    }
}