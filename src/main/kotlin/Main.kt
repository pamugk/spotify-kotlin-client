// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import spotify.api.SpotifyOAuth2Client
import spotify.api.service.getCurrentUser
import spotify.appModule
import java.awt.Desktop
import java.net.URI

@Composable
@Preview
fun App(loggedIn: Boolean) {
    Koin(appDeclaration = { modules(appModule) }) {
        val spotifyClient = get<SpotifyOAuth2Client>()
        MaterialTheme {
            Box {
                if (!loggedIn) {
                    Button(onClick = {
                        Desktop.getDesktop().browse(URI.create(spotifyClient.loginUrl))
                    }){}
                } else {
                    Text(spotifyClient.getCurrentUser().id)
                }
            }
        }
    }
}

fun main() = application {
    Koin(appDeclaration = { modules(appModule) }) {
        val spotifyClient = get<SpotifyOAuth2Client>()
        var loggedIn by mutableStateOf(false)
        spotifyClient.authenticationStatusChanged += { loggedIn = it }
        Window(onCloseRequest = ::exitApplication) {
            App(loggedIn)
        }
    }
}
