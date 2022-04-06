package spotify.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spotify.ui.resources.fontAwesomeFamily
import kotlin.time.Duration.Companion.milliseconds

@Composable
@Preview
fun PLayer() {
    val isFavorite = remember { mutableStateOf(false) }
    val isPlaying = remember { mutableStateOf(false) }
    val position = remember { mutableStateOf(0.milliseconds) }
    val duration = 66000.milliseconds
    val volume = remember { mutableStateOf(1f) }

    Row(Modifier.padding(16.dp, 0.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Button({}){
            Text("heart", fontFamily = fontAwesomeFamily,
                fontWeight = if (isFavorite.value) FontWeight.W900 else FontWeight.W400)
        }

        Column(Modifier.weight(0.6f), Arrangement.SpaceEvenly, Alignment.CenterHorizontally) {
            Row(Modifier, Arrangement.Center, Alignment.CenterVertically) {
                Button({}){
                    Text("shuffle", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                Button({}){
                    Text("backward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                Button({isPlaying.value = !isPlaying.value}){
                    Text(if (isPlaying.value) "circle-pause" else "circle-play", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                Button({}){
                    Text("forward-step", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
                Button({}){
                    Text("repeat", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
                }
            }
            Row(Modifier, Arrangement.SpaceAround, Alignment.CenterVertically) {
                Text(position.value.toString())
                Slider(
                    position.value.inWholeMilliseconds.toFloat(),
                    { position.value = it.toDouble().milliseconds },
                    Modifier.width(512.dp), true,
                    0f..duration.inWholeMilliseconds.toFloat()
                )
                Text(duration.toString())
            }
        }

        Button({}){
            Text("microphone", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
        }
        Button({}){
            Text("list", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
        }
        Button({}){
            Text("computer", fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)
        }
        Button({ volume.value = 0f }){
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