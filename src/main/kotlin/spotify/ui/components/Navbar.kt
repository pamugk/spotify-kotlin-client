package spotify.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import dev.burnoo.cokoin.get
import spotify.api.SpotifyOAuth2Client
import spotify.api.domain.User
import spotify.api.service.getCurrentUser
import spotify.ui.util.AsyncImage
import spotify.ui.util.loadImageBitmapAsync
import java.awt.Desktop
import java.net.URI

@Composable
@Preview
inline fun Navbar(
    modifier: Modifier = Modifier,
    secondaryContent: @Composable () -> Unit
) {
    val loggedIn = remember { mutableStateOf(false) }
    val user = remember { mutableStateOf<User?>(null) }
    val spotifyClient = get<SpotifyOAuth2Client>()
    spotifyClient.authenticationStatusChanged += { loggedIn.value = it }

    Row(modifier = modifier) {
        Button(onClick = {}){
            Text("<")
        }
        Button(onClick = {}){
            Text(">")
        }
        secondaryContent()
        Spacer(Modifier.weight(1f))

        if (loggedIn.value) {
            if (user.value == null) {
                LaunchedEffect(true) {
                    user.value = spotifyClient.getCurrentUser()
                }

                Button(onClick = {
                    spotifyClient.logOut()
                }){
                    CircularProgressIndicator()
                }
            } else {
                Button(onClick = {
                    spotifyClient.logOut()
                }){
                    if (user.value!!.images.isNotEmpty()) {
                        AsyncImage(
                            { loadImageBitmapAsync(user.value!!.images.last().url) },
                            { remember { BitmapPainter(it) } },
                            user.value!!.displayedName.orEmpty()
                        )
                    }
                    Text(user.value!!.displayedName.orEmpty())
                }
            }
        } else {
            Button(onClick = {
                Desktop.getDesktop().browse(URI.create(spotifyClient.loginUrl))
            }){
                Text("Вход")
            }
        }
    }
}