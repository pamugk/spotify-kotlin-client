// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberTrayState
import dev.burnoo.cokoin.Koin
import spotify.appModule
import spotify.ui.components.Drawer
import spotify.ui.components.Navbar
import spotify.ui.components.PLayer

@Composable
@Preview
fun App() {
    Koin(appDeclaration = { modules(appModule) }) {
        MaterialTheme {
            Column {
                Row(Modifier.weight(1f)) {
                    Drawer()
                    Column {
                        Navbar {}
                    }
                }
                PLayer()
            }
        }
    }
}

fun main() = application {
    val icon = painterResource("spotify/icon.svg")
    val trayState = rememberTrayState()

    Tray(
        state = trayState,
        icon = icon,
        menu = {
            Item("Выйти", onClick = ::exitApplication)
        }
    )
    Window(onCloseRequest = ::exitApplication, icon = icon, title = "Неофициальный клиент Spotify") {
        App()
    }
}
