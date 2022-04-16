package spotify.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import spotify.components.widgets.Drawer
import spotify.ui.resources.fontAwesomeFamily

@Composable
fun Drawer(component: Drawer, modifier: Modifier = Modifier) {
    Column(modifier.padding(8.dp).width(IntrinsicSize.Max)) {
        Image(painterResource("spotify/logo.svg"), "Spotify")
        TextButton({}, modifier = Modifier.fillMaxWidth()) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)) {
                    append("house")
                }
                append("Главная")
            }, modifier = Modifier.fillMaxWidth())
        }
        TextButton({}, modifier = Modifier.fillMaxWidth()) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)) {
                    append("magnifying-glass")
                }
                append("Поиск")
            }, modifier = Modifier.fillMaxWidth())
        }
        TextButton({}, modifier = Modifier.fillMaxWidth()) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)) {
                    append("bars")
                }
                append("Моя медиатека")
            }, modifier = Modifier.fillMaxWidth())
        }
        TextButton({}, modifier = Modifier.fillMaxWidth()) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)) {
                    append("square-plus")
                }
                append("Создать плейлист")
            }, modifier = Modifier.fillMaxWidth())
        }
        TextButton({}, modifier = Modifier.fillMaxWidth()) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontFamily = fontAwesomeFamily, fontWeight = FontWeight.W900)) {
                    append("heart")
                }
                append("Любимые треки")
            }, modifier = Modifier.fillMaxWidth())
        }
        Divider()
    }
}